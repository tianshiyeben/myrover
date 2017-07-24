package wg.myrover;

import org.apache.commons.lang3.StringUtils;

import wg.myrover.model.CpuState;
import wg.myrover.model.DeskState;
import wg.myrover.model.DiskIoState;
import wg.myrover.model.MemState;
import wg.myrover.model.NetIoState;
import wg.myrover.model.ProcessState;
import wg.myrover.model.SysLoadState;
import wg.myrover.model.TcpState;
import ch.ethz.ssh2.Connection;

/**
 * <p>Title:HostUtils </p>
 * <p>Description: 获取服务器信息的工具类</p>
 * @author yxz
 * @date Jul 24, 2017
 */
public class HostUtils {
	
	/**
	 * 获取系统版本信息
	 * @param conn
	 * @return
	 */
	public static String getSystemRelease(Connection conn){
		return CtrCommond.doCommond(conn, LinuxCmd.SYSTEM_RELEASE);
	}
	
	
	/**
	 * 获取系统版本详细信息
	 * @param conn
	 * @return
	 */
	public static String getSystemUname(Connection conn){
		return CtrCommond.doCommond(conn, LinuxCmd.UNAME_A);
	}
	
	/**
	 * 获取物理CPU个数
	 * @param conn
	 * @return
	 */
	public static String getCpuNum(Connection conn){
		return  CtrCommond.doCommond(conn, LinuxCmd.WULI_CPU_NUM);
	}

	
	/**
	 * 获取每个CPU核数量
	 * @param conn
	 * @return
	 */
	public static String getCpuPerCores(Connection conn){
		String str =  CtrCommond.doCommond(conn, LinuxCmd.WULI_CPU_CORE_NUM);
		if(!StringUtils.isEmpty(str)){
			return str.substring(str.length()-1);
		}
		return "";
	}
	
	
	/**
	 * 获取系统已经运行天数
	 * @param conn
	 * @return
	 */
	public static String getSystemDays(Connection conn){
		return LinuxStrUtil.getYxDays(CtrCommond.doCommond(conn, LinuxCmd.UPTIME));
	}
	
	
	/**
	 * 获取磁盘使用信息
	 * @param conn
	 * @return
	 */
	public static DeskState getDfInfo(Connection conn){
		return  LinuxStrUtil.setDfHl(CtrCommond.doCommond(conn, LinuxCmd.DF_HL));
	}
	
	/**
	 * 获取CPU型号信息
	 * @param conn
	 * @return
	 */
	public static String getCpuModel(Connection conn){
		String result =  CtrCommond.doCommond(conn, LinuxCmd.CPU_XINGHAO);
		if(!StringUtils.isEmpty(result)){
			return result.trim();
		}else{
			return "";
		}
	}
	
	/**
	 * 获取内存使用情况
	 * @param conn
	 * @return
	 */
	public static MemState getMemState(Connection conn){
		return  LinuxStrUtil.setViewMem(CtrCommond.doCommond(conn, LinuxCmd.VIEW_MEM));
	}
	
	
	/**
	 * 获取cpu使用情况
	 * @param conn
	 * @return
	 */
	public static CpuState getCpuState(Connection conn){
		return LinuxStrUtil.setCpuState(CtrCommond.doCommond(conn, LinuxCmd.VMSTAT));
	}
	
	
	/**
	 * 获取磁盘IO使用情况
	 * @param conn
	 * @return
	 */
	public static DiskIoState getDiskIoState(Connection conn){
		return LinuxStrUtil.setDiskIo(CtrCommond.doCommond(conn, LinuxCmd.DISK_IO));
	}
	
	
	/**
	 * 获取网络吞吐率
	 * @param conn
	 * @return
	 */
	public static NetIoState getNetIoState(Connection conn){
		return LinuxStrUtil.setNetIo(CtrCommond.doCommond(conn, LinuxCmd.SAR_DEV_1));
	}
	
	/**
	 * 获取tcp状态
	 * @param conn
	 * @return
	 */
	public static TcpState getTcpState(Connection conn){
		return LinuxStrUtil.setTcp(CtrCommond.doCommond(conn, LinuxCmd.SAR_TCP_1));
	}
	
	/**
	 * 获取系统负载状态
	 * @param conn
	 * @return
	 */
	public static SysLoadState getSysLoadState(Connection conn){
		return LinuxStrUtil.setSysLoad(CtrCommond.doCommond(conn, LinuxCmd.UPTIME));
	}
	
	
	/**
	 * 获取进程使用状态
	 * @param conn
	 * @param pid 进程ID
	 * @return
	 */
	public static ProcessState getProcessState(Connection conn,String pid){
		String processStr = CtrCommond.doCommond(conn, LinuxCmd.dd.replace("{pid}", pid));
		processStr =  conProcessStr(processStr,pid);
		String[] appStateStr = null;
		if(!StringUtils.isEmpty(processStr)){
			appStateStr = processStr.split(StaticKeys.SPLIT_KG);
			if(appStateStr.length>1){
				ProcessState processState = new ProcessState();
				processState.setCpuPer(appStateStr[0]);
				processState.setMemPer(appStateStr[1]);
				return processState;
			}
		}
		return null;
	}
	
	/**
	 * 检测系统已加载内核模块
	 * @param conn
	 * @return
	 */
	public static String getLsmodInfo(Connection conn){
		String lsmod = CtrCommond.doCommond(conn, LinuxCmd.lsmod);
		return lsmod;
	}
	
	/**
	 * 检测系统密码文件修改时间
	 * @param conn
	 * @return
	 */
	public static String getPasswdFileInfo(Connection conn){
		String passwdFile = CtrCommond.doCommond(conn, LinuxCmd.passwd_update_time);
		return passwdFile;
	}
	
	/**
	 * 检测系统rpc服务开放状态
	 * @param conn
	 * @return
	 */
	public static String getRpcinfo(Connection conn){
		String rpcinfo = CtrCommond.doCommond(conn, LinuxCmd.rpcinfo);
		return rpcinfo;
	}
	
	
	/**
	 * 检测系统任务计划
	 * @param conn
	 * @return
	 */
	public static String getCrontab(Connection conn){
		String crontab = CtrCommond.doCommond(conn, LinuxCmd.crontab);
		return crontab;
	}
	
	
	 /**
     * 对进行占用的CPU和mem字符串，进行预处理
     * @param gg
     * @return
     */
    private static String conProcessStr(String processStr,String pid){
    	if(StringUtils.isEmpty(processStr)){
			return "";
		}
    	processStr = processStr.trim();
		String[] rows = processStr.split(StaticKeys.SPLIT_BR);
		String[] cols = null;
		String row = "";
		for(int i = 1 ;i < rows.length; i++){
			row =  FormatUtil.replaceKg(rows[i]);
			cols = row.split(StaticKeys.SPLIT_KG);
			if(pid.equals(cols[1])){
				return cols[2]+" "+cols[3];
			}
		}
		return "";
    }

    
    
}

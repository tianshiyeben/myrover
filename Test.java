package wg.myrover;

import ch.ethz.ssh2.Connection;

/**
 * <p>Title:Test </p>
 * <p>Description:测试用例 </p>
 * <p>Company: www.wg900.com</p> 
 * @author yxz
 * @date Jul 24, 2017
 */
public class Test {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Connection conn = CtrCommond.getConn("192.168.1.248", "root", 22, "ecm.2016");
		System.out.println("CPU型号信息："+HostUtils.getCpuModel(conn));
		System.out.println("物理CPU个数："+HostUtils.getCpuNum(conn));
		System.out.println("每个CPU核数量："+HostUtils.getCpuPerCores(conn));
		System.out.println("系统运行天数："+HostUtils.getSystemDays(conn));
		System.out.println("系统版本信息："+HostUtils.getSystemRelease(conn));
		System.out.println("系统版本详细信息："+HostUtils.getSystemUname(conn));
		System.out.println("cpu Idle使用率："+HostUtils.getCpuState(conn).getIdle());
		System.out.println("磁盘已使用G："+HostUtils.getDfInfo(conn).getUsed());
		System.out.println("磁盘IO状态："+HostUtils.getDiskIoState(conn).getRkBS());
		System.out.println("内存已使用百分比："+HostUtils.getMemState(conn).getUsePer());
		System.out.println("网络吞吐率rxbyt："+HostUtils.getNetIoState(conn).getRxbyt());
		System.out.println("系统一分钟负载："+HostUtils.getSysLoadState(conn).getOneLoad());
		System.out.println("系统TCP active："+HostUtils.getTcpState(conn).getActive());
		//System.out.println("进程2696内存使用率："+HostUtils.getProcessState(conn, "2696").getMemPer());
		System.out.println("系统已加载内核模块："+HostUtils.getLsmodInfo(conn));
		System.out.println("系统密码文件修改时间："+HostUtils.getPasswdFileInfo(conn));
		System.out.println("系统rpc服务开放状态："+HostUtils.getRpcinfo(conn));
		System.out.println("当前系统任务计划："+HostUtils.getCrontab(conn));
	}

}

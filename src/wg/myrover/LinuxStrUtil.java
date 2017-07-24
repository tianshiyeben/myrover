package wg.myrover;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.apache.commons.lang3.StringUtils;

import wg.myrover.model.CpuState;
import wg.myrover.model.DeskState;
import wg.myrover.model.DiskIoState;
import wg.myrover.model.MemState;
import wg.myrover.model.NetIoState;
import wg.myrover.model.SysLoadState;
import wg.myrover.model.TcpState;

/**
 * 
* <p>Title:LinuxStrUtil </p>
* <p>Description: 处理linux命令执行后，返回的结果字符串</p>
* <p>Company: www.wg900.com</p> 
* @author yxz
* @date Jul 24, 2017
 */
public class LinuxStrUtil {

	
	private static final String COMMAND_NOT = "command not found";
	
	/**
	 * 处理磁盘使用信息
	 * @param df_hl
	 */
	public static DeskState setDfHl(String df_hl){
		if(StringUtils.isEmpty(df_hl)){
			return null;
		}
		df_hl = df_hl.trim();
		DeskState deskState = new DeskState();
		String[] rows = df_hl.split(StaticKeys.SPLIT_BR);
		String row = "";
		double sumPer = 0;
		double sumCon = 0;
		double sumUsed = 0;
		for(int i = 1 ;i < rows.length; i++){
			row =  FormatUtil.replaceKg(rows[i]);
			sumCon += FormatUtil.mToG(row.split(StaticKeys.SPLIT_KG)[1]);
			sumUsed += FormatUtil.mToG(row.split(StaticKeys.SPLIT_KG)[2]);
		}
		sumPer = (sumUsed/sumCon)*100;
		deskState.setUsePer(FormatUtil.formatDouble(sumPer, 2)+"");
		deskState.setSize(FormatUtil.formatDouble(sumCon, 2)+"");
		deskState.setUsed(FormatUtil.formatDouble(sumUsed, 2)+"");
		return deskState;
	}
	
	
	/**
	 * 处理磁盘IO使用信息
	 * @param df_hl
	 */
	public static DiskIoState setDiskIo(String diskIo){
		if(StringUtils.isEmpty(diskIo)){
			return  null;
		}
		if(diskIo.indexOf(COMMAND_NOT)>-1){
			return null;
		}
		diskIo = diskIo.trim();
		DiskIoState diskIoState = new DiskIoState();
		String[] rows = diskIo.split(StaticKeys.SPLIT_BR);
		String[] cols = null;
		String row = "";
		double rs = 0;
		double ws = 0;
		double rkBS = 0;
		double wkBS = 0;
		double await = 0;
		double avgquSz = 0;
		double util = 0;
		for(int i = 1 ;i < rows.length; i++){
			row =  FormatUtil.replaceKg(rows[i]);
			cols = row.split(StaticKeys.SPLIT_KG);
			rs = Double.valueOf(cols[3].trim())+rs;
			ws = Double.valueOf(cols[4].trim())+ws;
			rkBS = Double.valueOf(cols[5].trim())+rkBS;
			wkBS = Double.valueOf(cols[6].trim())+wkBS;
			avgquSz = Double.valueOf(cols[8].trim())+avgquSz;
			await = Double.valueOf(cols[9].trim())+await;
			util = Double.valueOf(cols[cols.length-1].trim())+util;
		}
		rs = rs/(rows.length-1);
		ws = ws/(rows.length-1);
		rkBS = rkBS/(rows.length-1);
		wkBS = wkBS/(rows.length-1);
		avgquSz = avgquSz/(rows.length-1);
		await = await/(rows.length-1);
		util = util/(rows.length-1);
		diskIoState.setRs(FormatUtil.formatDouble(rs, 2)+"");
		diskIoState.setWs(FormatUtil.formatDouble(ws, 2)+"");
		diskIoState.setRkBS(FormatUtil.formatDouble(rkBS, 2)+"");
		diskIoState.setWkBS(FormatUtil.formatDouble(wkBS, 2)+"");
		diskIoState.setAvgquSz(FormatUtil.formatDouble(avgquSz, 2)+"");
		diskIoState.setAwait(FormatUtil.formatDouble(await, 2)+"");
		diskIoState.setUtil(FormatUtil.formatDouble(util, 2)+"");
		return diskIoState;
	}
	
	/**
	 * 处理内存使用信息
	 * @param memstr
	 */
	public static MemState setViewMem(String memStr){
		if(StringUtils.isEmpty(memStr)){
			return null;
		}
		memStr = memStr.trim();
		MemState memState = new MemState();
		String[] rows = memStr.split(StaticKeys.SPLIT_BR);
		String row = "";
		for(int i = 1 ;i < 2; i++){
			row =  FormatUtil.replaceKg(rows[i]);
			memState.setTotal(row.split(StaticKeys.SPLIT_KG)[1]);
			memState.setUsed(row.split(StaticKeys.SPLIT_KG)[2]);
			memState.setFree(row.split(StaticKeys.SPLIT_KG)[3]);
		}
		try {
			double totalMem = Double.valueOf(memState.getTotal());
			double usedMem = Double.valueOf(memState.getUsed());
			double usedPer = (usedMem/totalMem)*100;
			memState.setUsePer(FormatUtil.formatDouble(usedPer, 1)+"");
		} catch (Exception e) {
			e.printStackTrace();
		}
		return memState;
	}
	
	/**
	 * 处理cpu使用情况
	 * @param hostname
	 * @param memStr
	 */
	public static CpuState setCpuState(String memStr){
		if(StringUtils.isEmpty(memStr)){
			return null;
		}
		if(memStr.indexOf(COMMAND_NOT)>-1){
			return null;
		}
		memStr = memStr.trim();
		CpuState cpuState = new CpuState();
		String[] rows = memStr.split(StaticKeys.SPLIT_BR);
		String row = "";
		String[] cols = null;
		for(int i = 1 ;i < rows.length; i++){
			row =  FormatUtil.replaceKg(rows[i]);
			cols = row.split(StaticKeys.SPLIT_KG);
			if(cols[0].contains(":")){
				row =  FormatUtil.replaceKg(rows[i]);
				cols = row.split(StaticKeys.SPLIT_KG);
				cpuState.setUser(cols[2]);
				cpuState.setSys(cols[4]);
				cpuState.setIowait(cols[5]);
				cpuState.setIrq(cols[6]);
				cpuState.setSoft(cols[7]);
				cpuState.setIdle(cols[10]);
				break;
			}
		}
		return cpuState;
	}
	
	
	/**
	 * 处理网络设备吞吐率
	 * @param hostname
	 * @param memStr
	 */
	public static NetIoState setNetIo(String netIoStr){
		if(StringUtils.isEmpty(netIoStr)){
			return null;
		}
		if(netIoStr.indexOf(COMMAND_NOT)>-1){
			return null;
		}
		netIoStr = netIoStr.trim();
		NetIoState netIoState = new NetIoState();
		String[] rows = netIoStr.split(StaticKeys.SPLIT_BR);
		String row = "";
		String[] cols = null;
		double rxpck = 0;
		double txpck = 0;
		double rxbyt = 0;//rxkB/s
		double txbyt = 0;//txkB/s
		double rxcmp = 0;
		double txcmp = 0;
		for(int i = 1 ;i < rows.length; i++){
			row =  FormatUtil.replaceKg(rows[i]);
			cols = row.split(StaticKeys.SPLIT_KG);
			if(cols[0].contains(":")&&cols[1].contains("eth")){
				rxpck = Double.valueOf(cols[2].trim())+rxpck;
				txpck = Double.valueOf(cols[3].trim())+txpck;
				rxbyt = Double.valueOf(cols[4].trim())+rxbyt;
				txbyt = Double.valueOf(cols[5].trim())+txbyt;
				rxcmp = Double.valueOf(cols[6].trim())+rxcmp;
				txcmp = Double.valueOf(cols[7].trim())+txcmp;
			}
		}
		netIoState.setRxpck(FormatUtil.formatDouble(rxpck, 2)+"");
		netIoState.setTxpck(FormatUtil.formatDouble(txpck, 2)+"");
		netIoState.setRxbyt(FormatUtil.formatDouble(rxbyt, 2)+"");//rxkB/s
		netIoState.setTxbyt(FormatUtil.formatDouble(txbyt, 2)+"");//txkB/s
		netIoState.setRxcmp(FormatUtil.formatDouble(rxcmp, 2)+"");
		netIoState.setTxcmp(FormatUtil.formatDouble(txcmp, 2)+"");
		return netIoState;
	}
	
	
	/**
	 * 处理TCP连接状态字符串
	 * @param hostname
	 * @param tcpStr
	 */
	public static TcpState setTcp(String tcpStr){
		if(StringUtils.isEmpty(tcpStr)){
			return null;
		}
		if(tcpStr.indexOf(COMMAND_NOT)>-1){
			return null;
		}
		tcpStr = tcpStr.trim();
		TcpState tcpState = new TcpState();
		String[] rows = tcpStr.split(StaticKeys.SPLIT_BR);
		String row = "";
		String[] cols = null;
		if(rows.length>1){
			row =  FormatUtil.replaceKg(rows[rows.length-1]);
			cols = row.split(StaticKeys.SPLIT_KG);
			if(cols[0].contains(":")){
				tcpState.setActive(cols[1]);
				tcpState.setPassive(cols[2]);
			}
		}
		return tcpState;
	}
	

	/**
	 * 获取系统已经运行天数
	 * @param hostname
	 * @param loadStr
	 */
	public static String getYxDays(String loadStr){
		if(StringUtils.isEmpty(loadStr)){
			return null;
		}
		String days =  "";
		Pattern pattern = Pattern.compile("up.*days"); 
	    Matcher match = pattern.matcher(loadStr.toLowerCase()); 
	    if(match.find()){
	    	days =  match.group(); 
	    }
	    days = days.replace("up", "").replace("days", "");
		return days.replace(StaticKeys.SPLIT_KG, "");
	}
	
	/**
	 * 处理系统负载字符串
	 * @param hostname
	 * @param loadStr
	 */
	public static SysLoadState setSysLoad(String loadStr){
		if(StringUtils.isEmpty(loadStr)){
			return null;
		}
		String users =  "0";
		String average = "average:";
		int userIndex = loadStr.indexOf("user");
		users = loadStr.substring(0, userIndex).substring(userIndex-2);
		SysLoadState loadState = new SysLoadState();
		String minLoad = loadStr.substring(loadStr.indexOf(average)+average.length());
		String[] cols =  minLoad.split(StaticKeys.SPLIT_DH);
		loadState.setUsers(users.replace(StaticKeys.SPLIT_KG, ""));
		loadState.setOneLoad(cols[0].replace(StaticKeys.SPLIT_DH, "").replace(StaticKeys.SPLIT_KG, ""));
		loadState.setFiveLoad(cols[1].replace(StaticKeys.SPLIT_DH, "").replace(StaticKeys.SPLIT_KG, ""));
		loadState.setFifteenLoad(cols[2].replace(StaticKeys.SPLIT_DH, "").replace(StaticKeys.SPLIT_KG, ""));
		return loadState;
	}
	

}

package wg.myrover.model;

import java.io.Serializable;

/**
 * 
* <p>Title:ProcessState </p>
* <p>Description: 进程状态</p>
* <p>Company: www.wg900.com</p> 
* @author yxz
* @date Jul 24, 2017
 */
public class ProcessState implements Serializable{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -2913111613773445949L;

	/**
	 * %CPU
	 */
    private String cpuPer;

    /**
	 * %MEM
	 */
    private String memPer;

	public String getCpuPer() {
		return cpuPer;
	}

	public void setCpuPer(String cpuPer) {
		this.cpuPer = cpuPer;
	}


	public String getMemPer() {
		return memPer;
	}

	public void setMemPer(String memPer) {
		this.memPer = memPer;
	}

   
}
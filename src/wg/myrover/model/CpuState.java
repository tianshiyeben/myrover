package wg.myrover.model;

import java.io.Serializable;

/**
 * 
* <p>Title:CpuState </p>
* <p>Description: 查看CPU使用情况</p>
* @author yxz
* @date Jul 24, 2017
 */
public class CpuState implements Serializable{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -2913111613773445949L;

	/**
	 * 用户态的CPU时间（%）
	 */
    private String user;

    /**
	 * 系统（内核）时间（%）
	 */
    private String sys;
    
    /**
	 * 空闲时间（idle）（%）
	 */
    private String idle;
    
    /**
   	 * IO等待时间（wait）（%）
   	 */
    private String iowait;
    
    /**
   	 * 硬中断时间（%）
   	 */
    private String irq;
    
    /**
     * 软中断时间（%）
     */
    private String soft;

	public String getUser() {
		return user;
	}

	public void setUser(String user) {
		this.user = user;
	}

	public String getSys() {
		return sys;
	}

	public void setSys(String sys) {
		this.sys = sys;
	}

	public String getIdle() {
		return idle;
	}

	public void setIdle(String idle) {
		this.idle = idle;
	}

	public String getIowait() {
		return iowait;
	}

	public void setIowait(String iowait) {
		this.iowait = iowait;
	}

	public String getIrq() {
		return irq;
	}

	public void setIrq(String irq) {
		this.irq = irq;
	}

	public String getSoft() {
		return soft;
	}

	public void setSoft(String soft) {
		this.soft = soft;
	}

}

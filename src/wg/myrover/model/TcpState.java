package wg.myrover.model;

import java.io.Serializable;

/**
 * 
* <p>Title:TcpState </p>
* <p>Description: 查看TCP连接状态</p>
* @author yxz
* @date Jul 24, 2017
 */
public class TcpState implements Serializable{
	

	/**
	 * 
	 */
	private static final long serialVersionUID = -299667815095138020L;


	/**
	 * 每秒本地发起的TCP连接数，既通过connect调用创建的TCP连接；,active/s
	 */
    private String active;

    /**
	 * 每秒远程发起的TCP连接数，即通过accept调用创建的TCP连接,passive/s
	 */
    private String passive;
    
    
	public String getActive() {
		return active;
	}

	public void setActive(String active) {
		this.active = active;
	}

	public String getPassive() {
		return passive;
	}

	public void setPassive(String passive) {
		this.passive = passive;
	}

}

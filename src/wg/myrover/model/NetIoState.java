package wg.myrover.model;

import java.io.Serializable;

/**
 * 
* <p>Title:NetIoState </p>
* <p>Description:网络设备的吞吐率 </p>
* <p>Company: www.wg900.com</p> 
* @author yxz
* @date Jul 24, 2017
 */
public class NetIoState implements Serializable{
	

	/**
	 * 
	 */
	private static final long serialVersionUID = -8314012397341825158L;

	
	/**
	 * 每秒钟接收的数据包,rxpck/s
	 */
    private String rxpck;

    /**
	 * 每秒钟发送的数据包,txpck/s
	 */
    private String txpck;
    
    
    /**
	 * 每秒钟接收的KB数,rxkB/s
	 */
    private String rxbyt;

    /**
	 * 每秒钟发送的KB数,txkB/s
	 */
    private String txbyt;
    
    /**
	 * 每秒钟接收的压缩数据包,rxcmp/s
	 */
    private String rxcmp;
    
    
    /**
	 * 每秒钟发送的压缩数据包,txcmp/s
	 */
    private String txcmp;
    
    
	public String getRxpck() {
		return rxpck;
	}

	public void setRxpck(String rxpck) {
		this.rxpck = rxpck;
	}

	public String getTxpck() {
		return txpck;
	}

	public void setTxpck(String txpck) {
		this.txpck = txpck;
	}

	public String getRxbyt() {
		return rxbyt;
	}

	public void setRxbyt(String rxbyt) {
		this.rxbyt = rxbyt;
	}

	public String getTxbyt() {
		return txbyt;
	}

	public void setTxbyt(String txbyt) {
		this.txbyt = txbyt;
	}

	public String getRxcmp() {
		return rxcmp;
	}

	public void setRxcmp(String rxcmp) {
		this.rxcmp = rxcmp;
	}

	public String getTxcmp() {
		return txcmp;
	}

	public void setTxcmp(String txcmp) {
		this.txcmp = txcmp;
	}

}
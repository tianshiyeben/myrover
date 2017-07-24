package wg.myrover.model;

import java.io.Serializable;

/**
 * 
* <p>Title:SysLoadState </p>
* <p>Description: uptime查看系统负载状态</p>
* <p>Company: www.wg900.com</p> 
* @author yxz
* @date Jul 24, 2017
 */
public class SysLoadState implements Serializable{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -4863071148000213553L;


	/**
	 * 1分钟之前到现在的负载
	 */
    private String oneLoad;

    /**
	 *5分钟之前到现在的负载
	 */
    private String fiveLoad;
    
    /**
   	 *15分钟之前到现在的负载
   	 */
    private String fifteenLoad;
    
    /**
     * 登录用户数量
     */
    private String users;
    
	public String getOneLoad() {
		return oneLoad;
	}

	public void setOneLoad(String oneLoad) {
		this.oneLoad = oneLoad;
	}

	public String getFiveLoad() {
		return fiveLoad;
	}

	public void setFiveLoad(String fiveLoad) {
		this.fiveLoad = fiveLoad;
	}

	public String getFifteenLoad() {
		return fifteenLoad;
	}

	public void setFifteenLoad(String fifteenLoad) {
		this.fifteenLoad = fifteenLoad;
	}
	
	public String getUsers() {
		return users;
	}

	public void setUsers(String users) {
		this.users = users;
	}

}
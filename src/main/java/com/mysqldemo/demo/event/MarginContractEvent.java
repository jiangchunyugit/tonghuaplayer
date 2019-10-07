package com.mysqldemo.demo.event;


import com.mysqldemo.demo.BaseModel;

/**
 * @author jiangchunyu(后台)
 * @date 20181109
 * @Description 合同相关数据事件
 */
public class MarginContractEvent extends BaseModel {

    private String source;
    /**
     * 获取 事件源
     *
     * @return
     */
    public String getSource() {
        return source;
    }

    public MarginContractEvent(String projectNo){
        this.source = projectNo;
    }

	public MarginContractEvent(String contractNumber, String signedTime) {
		// TODO Auto-generated constructor stub
		this.source  = contractNumber+"@"+signedTime;
	}

}

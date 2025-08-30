package cn.yunmeng.techbotany.api.dao;

import java.util.HashMap;

/**
 * 数据序列化接口
 */
public interface DataSerializable {

    HashMap<String, Object> serialize();

}

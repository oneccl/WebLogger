package com.cc.weblogger.pojo;


import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Created with IntelliJ IDEA.
 * Author: CC
 * E-mail: 203717588@qq.com
 * Date: 2023/2/28
 * Time: 11:11
 * Description:
 */

@Data
@AllArgsConstructor
@NoArgsConstructor

@TableName("syslog")  // 表名(必须存在)
public class SysLog {

    /*
    id为Long类型，数据库中id为bigint类型，mybatis有自己的一套id生成方案，生成的id必须是Long类型
    */
    @TableId(type= IdType.AUTO) // 自增
    private Long id;

    private String dt;
    // 驼峰命名对应数据库表字段下划线(下同)
    private String className;   // class_name
    private String methodName;  // method_name
    private String ip;
    private String host;
    private String reqMethod;   // req_method
    private String protocol;
    private String url;
    private String contentType; // content_type
    private String statusCode;  // status_code

}

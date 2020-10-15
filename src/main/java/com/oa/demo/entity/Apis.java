package com.oa.demo.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import java.io.Serializable;

/**
 * <p>
 * 
 * </p>
 *
 * @author valonYe
 * @since 2020-10-15
 */
public class Apis implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * user ID
     */
      @TableId(value = "id", type = IdType.AUTO)
    private Long id;

    /**
     * group name
     */
    private String groupName;

    /**
     * env name
     */
    private String envName;

    /**
     * api address
     */
    private String apiUrl;

    /**
     * api name
     */
    private String apiName;

    /**
     * http method
     */
    private String httpMethod;


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getGroupName() {
        return groupName;
    }

    public void setGroupName(String groupName) {
        this.groupName = groupName;
    }

    public String getEnvName() {
        return envName;
    }

    public void setEnvName(String envName) {
        this.envName = envName;
    }

    public String getApiUrl() {
        return apiUrl;
    }

    public void setApiUrl(String apiUrl) {
        this.apiUrl = apiUrl;
    }

    public String getApiName() {
        return apiName;
    }

    public void setApiName(String apiName) {
        this.apiName = apiName;
    }

    public String getHttpMethod() {
        return httpMethod;
    }

    public void setHttpMethod(String httpMethod) {
        this.httpMethod = httpMethod;
    }

    @Override
    public String toString() {
        return "Apis{" +
        "id=" + id +
        ", groupName=" + groupName +
        ", envName=" + envName +
        ", apiUrl=" + apiUrl +
        ", apiName=" + apiName +
        ", httpMethod=" + httpMethod +
        "}";
    }
}

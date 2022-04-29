package com.openStack;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.openstack4j.model.common.Link;
import org.openstack4j.model.compute.builder.FlavorBuilder;
import org.openstack4j.openstack.common.GenericLink;
import org.openstack4j.openstack.compute.domain.NovaFlavor;

import java.util.List;

@Data
@AllArgsConstructor
public class Flavor extends NovaFlavor{

    private static final long serialVersionUID = 1L;
    // ID
    private String id;
    // 名称
    private String name;
    // 内存 (MB)
    private int ram;
    // VCPU数量
    private int vcpus;
    //根磁盘(GB)
    private int disk;
    // 临时磁盘(GB)
    @JsonProperty("OS-FLV-EXT-DATA:ephemeral")
    private int ephemeral;
    // Swap磁盘(MB)
    private int swap;
    // RX/TX 因子
    @JsonProperty("rxtx_factor")
    private float rxtxFactor = 1.0F;

    public Flavor() {
    }

}
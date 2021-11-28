package com.hanium.chungyakpassback.entity.apt;

import javax.persistence.Column;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import java.io.Serializable;

public class AptInfoTargetSpecialKey implements Serializable {

    private String housingType;

    private Integer aptInfo;
}


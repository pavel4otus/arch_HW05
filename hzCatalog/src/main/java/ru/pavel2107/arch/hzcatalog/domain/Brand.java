package ru.pavel2107.arch.hzcatalog.domain;

import lombok.Data;
import lombok.NoArgsConstructor;
import java.io.Serializable;

@Data
@NoArgsConstructor
public class Brand extends CommonTypeWithId implements Serializable {
    String name;
    String code;
    String description;
    String logoUrl;
}

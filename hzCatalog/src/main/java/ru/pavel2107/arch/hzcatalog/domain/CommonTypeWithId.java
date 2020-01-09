package ru.pavel2107.arch.hzcatalog.domain;


import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Data
@NoArgsConstructor
public class CommonTypeWithId implements Serializable {
    private Long id;
}

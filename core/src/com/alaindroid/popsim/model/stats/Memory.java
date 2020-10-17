package com.alaindroid.popsim.model.stats;

import lombok.Data;
import lombok.experimental.Accessors;

import java.util.ArrayList;
import java.util.List;

@Data
@Accessors(fluent = true)
public class Memory {
    private List<CreatureSnapshot> memory = new ArrayList<>();

}

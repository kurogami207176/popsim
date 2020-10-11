package com.alaindroid.popsim.model;

import com.alaindroid.popsim.model.features.Trait;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.experimental.Accessors;

import java.util.List;

@Data
@Accessors(fluent = true)
@AllArgsConstructor
public class Terrain {
    private List<Trait> foods;
}

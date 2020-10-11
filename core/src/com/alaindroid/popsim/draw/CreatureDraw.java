package com.alaindroid.popsim.draw;

import com.alaindroid.popsim.model.Creature;
import com.badlogic.gdx.graphics.Texture;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.experimental.Accessors;

@Data
@AllArgsConstructor
@Accessors(fluent = true)
public class CreatureDraw {
    private Creature creature;

    private Texture img;
}

package com.alaindroid.popsim.draw;

import com.alaindroid.popsim.model.Creature;
import com.alaindroid.popsim.model.action.Action;
import com.alaindroid.popsim.model.action.ActionType;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.experimental.Accessors;

@Data
@AllArgsConstructor
@Accessors(fluent = true)
public class CreatureDraw {
    private static final int BAR_DIST = 2;
    private static Color FIND_FOOD = Color.RED;
    private static Color WANDER = Color.WHITE;
    private static Color EAT = Color.FOREST;
    private Creature creature;

    private Integer drawLevel;
    private Texture img;
    private Color color;
    private float radius = 5;

    public void draw(SpriteBatch batch) {
        // batch.draw(img(), creature().location().x(), creature().location().y());
    }
    public void draw(ShapeRenderer shapeRenderer) {
        float healthPercent = creature.body().healthPercent();
        float x = creature.location().x();
        float y = creature.location().y();
        drawCreature(shapeRenderer, healthPercent, x, y);

        drawActionPath(shapeRenderer, creature.action(), x, y);
        drawReach(shapeRenderer, x, y);
    }

    private void drawReach(ShapeRenderer shapeRenderer, float x, float y) {
        shapeRenderer.begin(ShapeRenderer.ShapeType.Line);
        if (creature.reach() != null) {
            shapeRenderer.circle(x, y, creature.reach().reach());
        }
        shapeRenderer.end();
    }

    private void drawActionPath(ShapeRenderer shapeRenderer, Action action, float x, float y) {
        shapeRenderer.begin(ShapeRenderer.ShapeType.Filled);
        if (action != null) {
            switch (action.actionType()) {
                case WANDER:
                    shapeRenderer.setColor(WANDER);
                    break;
                case FIND_FOOD:
                    shapeRenderer.setColor(FIND_FOOD);
                    break;
                case EAT:
                    shapeRenderer.setColor(EAT);
                    break;
            }
        }
        if (creature.action() != null) {
            float xt = creature.action().target().x();
            float yt = creature.action().target().y();
            shapeRenderer.line(x, y, xt, yt);
        }
        shapeRenderer.end();
    }

    private void drawCreature(ShapeRenderer shapeRenderer, float healthPercent, float x, float y) {
        shapeRenderer.begin(ShapeRenderer.ShapeType.Filled);
        shapeRenderer.setColor(color());
        shapeRenderer.circle(x, y, radius);
        float x0 = x - radius;
        float x1 = x0 + (2 * radius) * healthPercent;
        float y0 = y - radius - BAR_DIST;
        float y1 = y - radius - BAR_DIST;
        shapeRenderer.rectLine(x0, y0, x1, y1, 2);
        shapeRenderer.end();
    }
}

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
    private static final int BAR_WIDTH = 6;
    private static Color DEAD = Color.RED;
    private static Color FIND_FOOD = Color.YELLOW;
    private static Color WANDER = Color.WHITE;
    private static Color EAT = Color.GREEN;
    private Creature creature;

    private Integer drawLevel;
    private Texture img;
    private Color color;
    private float radius;

    public void draw(SpriteBatch batch) {
        // batch.draw(img(), creature().location().x(), creature().location().y());
    }
    public void draw(ShapeRenderer shapeRenderer) {
        float x = creature.location().x();
        float y = creature.location().y();
        drawCreature(shapeRenderer, x, y);

        drawActionPath(shapeRenderer, x, y);
        drawReach(shapeRenderer, creature.desire(), x, y);
    }

    private void drawReach(ShapeRenderer shapeRenderer, ActionType desiredActionType, float x, float y) {
        shapeRenderer.begin(ShapeRenderer.ShapeType.Line);
        matchColor(shapeRenderer, desiredActionType);
        if (creature.reach() != null) {
            shapeRenderer.circle(x, y, creature.reach().reach());
        }
        shapeRenderer.end();
    }

    private void drawActionPath(ShapeRenderer shapeRenderer, float x, float y) {
        shapeRenderer.begin(ShapeRenderer.ShapeType.Filled);
        if (creature.action() != null) {
            matchColor(shapeRenderer, creature.action().actionType());
        }
        if (creature.action() != null) {
            if(creature.action().actionType() != ActionType.DEAD) {
                shapeRenderer.line(x, y,
                        creature.action().target().x(),
                        creature.action().target().y());
            }
            else {
                float w = 2 * radius;
                shapeRenderer.line(x - w, y - w, x + w, y + w);
                shapeRenderer.line(x + w, y - w, x - w, y + w);
            }
        }
        shapeRenderer.end();
    }

    private void drawCreature(ShapeRenderer shapeRenderer, float x, float y) {
        shapeRenderer.begin(ShapeRenderer.ShapeType.Filled);
        shapeRenderer.setColor(color());
        shapeRenderer.circle(x, y, radius);
        float x0 = x - BAR_WIDTH;
        float x1 = x0 + (2 * BAR_WIDTH) * creature.body().healthPercent();
        float y0 = y - radius - BAR_DIST;
        float y1 = y - radius - BAR_DIST;
        shapeRenderer.rectLine(x0, y0, x1, y1, 3);
        // hunger
        if (creature.hunger().isHungry()) {
            float xt0 = x;
            float yt0 = y + radius * 2;
            float xt1 = x - radius;
            float yt1 = y + radius;
            float xt2 = x + radius;
            float yt2 = y + radius;
            shapeRenderer.triangle(xt0, yt0, xt1, yt1, xt2, yt2);
        }
        shapeRenderer.end();
    }

    private void matchColor(ShapeRenderer shapeRenderer, ActionType actionType) {
        switch (actionType) {
            case WANDER:
                shapeRenderer.setColor(WANDER);
                break;
            case FIND_FOOD:
                shapeRenderer.setColor(FIND_FOOD);
                break;
            case EAT:
                shapeRenderer.setColor(EAT);
                break;
            case DEAD:
                shapeRenderer.setColor(DEAD);
                break;
        }
    }

}

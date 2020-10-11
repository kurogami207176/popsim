package com.alaindroid.popsim.service.seeker;

import com.alaindroid.popsim.model.Creature;
import com.alaindroid.popsim.model.features.Location;

import java.util.List;

public interface TargetFinderService {
    Location findTarget(Creature creature, List<Creature> otherLives);
}

package com.alaindroid.popsim.modules;

import com.alaindroid.popsim.draw.DrawBox;
import com.alaindroid.popsim.service.ActionDecisionService;
import com.alaindroid.popsim.service.ActionFinderService;
import com.alaindroid.popsim.service.CreaturesService;
import com.alaindroid.popsim.service.MobilityService;
import com.alaindroid.popsim.service.seeker.DeadActionService;
import com.alaindroid.popsim.service.seeker.EatService;
import com.alaindroid.popsim.service.seeker.FoodSeekerService;
import com.alaindroid.popsim.service.seeker.WanderService;
import dagger.Module;
import dagger.Provides;

import javax.inject.Singleton;

@Module
public class ServiceModule {

    @Provides
    @Singleton
    public DrawBox drawBox() {
        return new DrawBox(0, 100, 0, 100);
    }

    @Provides
    @Singleton
    public ActionDecisionService actionDecisionService() {
        return new ActionDecisionService();
    }

    @Provides
    @Singleton
    public ActionFinderService actionFinderService(FoodSeekerService foodSeekerService,
                                                   WanderService wanderService,
                                                   EatService eatService,
                                                   DeadActionService deadActionService) {
        return new ActionFinderService(foodSeekerService, wanderService, eatService, deadActionService);
    }

    @Provides
    @Singleton
    public MobilityService mobilityService(ActionDecisionService actionDecisionService,
                                           ActionFinderService actionFinderService, DrawBox drawBox) {
        return new MobilityService(actionDecisionService, actionFinderService, drawBox);
    }

    @Provides
    @Singleton
    public CreaturesService creaturesService(MobilityService mobilityService) {
        return new CreaturesService(mobilityService);
    }

    @Provides
    @Singleton
    public FoodSeekerService foodSeekerService(WanderService wanderService) {
        return new FoodSeekerService(wanderService);
    }
    @Provides
    @Singleton
    public WanderService wanderService(DrawBox drawBox) {
        return new WanderService(drawBox);
    }

    @Provides
    @Singleton
    public EatService eatService(WanderService wanderService) {
        return new EatService(wanderService);
    }

    @Provides
    @Singleton
    public DeadActionService deadActionService() {
        return new DeadActionService();
    }
}

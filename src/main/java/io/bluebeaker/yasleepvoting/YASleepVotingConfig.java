package io.bluebeaker.yasleepvoting;

import net.minecraftforge.common.config.Config;
import net.minecraftforge.common.config.Config.Comment;
import net.minecraftforge.common.config.Config.LangKey;
import net.minecraftforge.common.config.Config.Type;

@Config(modid = YASleepVoting.MODID,type = Type.INSTANCE,category = "general")
public class YASleepVotingConfig {
    @Comment({"Sleep Voting Percentage. When (sleeping players / non-spectator players)>this value, the night/thunder will be skipped. ",
            "Values above 100 disables skipping night/thunder by sleeping. "})
    @LangKey("config.yasleepvoting.sleepVotePercentage.name")
    @Config.RangeDouble(min = 0.0,max = 101.0)
    public static float sleepVotePercentage = 0.0f;
    public static boolean showDebugMessages = false;
}
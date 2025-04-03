package io.bluebeaker.yasleepvoting;

import net.minecraftforge.common.config.Config;
import net.minecraftforge.common.config.Config.Comment;
import net.minecraftforge.common.config.Config.LangKey;
import net.minecraftforge.common.config.Config.Type;

@Config(modid = YASleepVoting.MODID,type = Type.INSTANCE,category = "general")
public class YASleepVotingConfig {
    @Comment("Sleep Voting Percentage. When (sleeping players / non-spectator players)>this value, the night/thunder will be skipped. ")
    @LangKey("config.yasleepvoting.sleepVotePercentage.name")
    public static float sleepVotePercentage = 0.0f;
}
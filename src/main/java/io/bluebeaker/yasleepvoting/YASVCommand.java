package io.bluebeaker.yasleepvoting;

import net.minecraft.command.*;
import net.minecraft.server.MinecraftServer;
import net.minecraftforge.common.config.Config;
import net.minecraftforge.common.config.ConfigManager;

public class YASVCommand extends CommandBase {
    @Override
    public String getName() {
        return "yasleepvoting";
    }

    @Override
    public String getUsage(ICommandSender sender) {
        return "commands.yasleepvoting.usage";
    }

    @Override
    public void execute(MinecraftServer server, ICommandSender sender, String[] args) throws CommandException {
        if(args.length<1){
            notifyCommandListener(sender, this, "commands.yasleepvoting.get", YASleepVotingConfig.sleepVotePercentage);
        }
        YASleepVotingConfig.sleepVotePercentage= (float) parseDouble(args[0],0f,101f);

        notifyCommandListener(sender, this, "commands.yasleepvoting.success", YASleepVotingConfig.sleepVotePercentage);
        ConfigManager.sync(YASleepVoting.MODID, Config.Type.INSTANCE);
    }
}

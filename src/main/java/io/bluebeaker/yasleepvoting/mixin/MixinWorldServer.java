package io.bluebeaker.yasleepvoting.mixin;

import io.bluebeaker.yasleepvoting.YASleepVoting;
import io.bluebeaker.yasleepvoting.YASleepVotingConfig;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.world.WorldServer;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.Redirect;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(WorldServer.class)
public class MixinWorldServer {
    @Shadow
    private boolean allPlayersSleeping;

    @Inject(method = "updateAllPlayersSleepingFlag", at = @At("HEAD"), cancellable = true)
    public void updateSleepingFlag(CallbackInfo ci){
        if(YASleepVotingConfig.sleepVotePercentage>=100) return;

        this.allPlayersSleeping=false;
        WorldServer server = (WorldServer) (Object) this;
        if (!server.playerEntities.isEmpty())
        {
            int spectators = 0;
            int sleepingPlayers = 0;

            for (EntityPlayer entityplayer : server.playerEntities)
            {
                if (entityplayer.isSpectator())
                {
                    ++spectators;
                }
                else if (entityplayer.isPlayerSleeping())
                {
                    ++sleepingPlayers;
                }
            }

            this.allPlayersSleeping = (sleepingPlayers > 0) && ((sleepingPlayers / (float) (server.playerEntities.size() - spectators) * 100) >= YASleepVotingConfig.sleepVotePercentage);

            YASleepVoting.getLogger().info("Sleeping percentage: {}, {}",((sleepingPlayers / (float) (server.playerEntities.size() - spectators) * 100)),this.allPlayersSleeping);
        }
        ci.cancel();
    }

    @Redirect(method = "areAllPlayersAsleep", at = @At(value = "INVOKE", target = "Lnet/minecraft/entity/player/EntityPlayer;isPlayerFullyAsleep()Z"))
    public boolean sleepCheck(EntityPlayer player){
        if(!player.isPlayerSleeping()){
            return true;
        }
        return player.isPlayerFullyAsleep();
    }
}

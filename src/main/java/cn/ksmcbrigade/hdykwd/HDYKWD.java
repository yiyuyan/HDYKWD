package cn.ksmcbrigade.hdykwd;

import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.fml.common.Mod;


@Mod("hdykwd")
public class HDYKWD {

    public HDYKWD() {
        MinecraftForge.EVENT_BUS.register(this);
    }
}

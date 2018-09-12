package io.anuke.mindustry.world.modules;

import io.anuke.mindustry.world.blocks.power.PowerGraph;

import java.io.DataInput;
import java.io.DataOutput;
import java.io.IOException;

public class PowerModule extends BlockModule{
    public float amount;
    public float capacity = 10f;
    public float voltage = 0.0001f;
    public PowerGraph graph;

    public boolean acceptsPower(){
        return amount + 0.001f <= capacity;
    }

    public float addPower(float add){
        if(add < voltage){
            return add;
        }

        float canAccept = Math.min(capacity - amount, add);

        amount += canAccept;

        return canAccept;
    }

    @Override
    public void write(DataOutput stream) throws IOException{
        stream.writeFloat(amount);
    }

    @Override
    public void read(DataInput stream) throws IOException{
        amount = stream.readFloat();
        if(Float.isNaN(amount)){
            amount = 0f;
        }
    }
}

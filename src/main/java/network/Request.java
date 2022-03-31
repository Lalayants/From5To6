package network;

import commands.Commandable;
import commands.Invoker;

import java.io.Serializable;

public class Request implements Serializable {
    private Commandable command ;
    private Object args;
    private Object extra;

    public Request(String comm, Object arg){
        command = new Invoker().commands.get(comm);
        args = arg;
    }

    public Object getExtra() {
        return extra;
    }

    public void setExtra(Object extra) {
        this.extra = extra;
    }

    public Commandable getCommand() {
        return command;
    }

    public Object getArgs() {
        return args;
    }
}

package com.rtm516.stackparser;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.function.Supplier;

import static org.junit.jupiter.api.Assertions.*;

class ParserTest {

    private static Map<String, StackTestOutput> errors = new HashMap<>();

    @BeforeAll
    static void setup() {
        errors.put("[21:22:19] [Thread-1/WARN]: java.lang.Throwable: Server stopped\n" +
                "[21:22:19] [Thread-1/WARN]: \tat io.papermc.paper.util.TraceUtil.dumpTraceForThread(TraceUtil.java:16)\n" +
                "[21:22:19] [Thread-1/WARN]: \tat net.minecraft.server.v1_16_R3.MinecraftServer.safeShutdown(MinecraftServer.java:992)\n" +
                "[21:22:19] [Thread-1/WARN]: \tat org.bukkit.craftbukkit.v1_16_R3.util.ServerShutdownThread.run(ServerShutdownThread.java:16)",
                new StackTestOutput("java.lang.Throwable", "Server stopped", 3));

        errors.put("java.lang.IllegalStateException: Unregistered dimension type: net.minecraft.server.v1_16_R3.DimensionManager@15967d7e\n" +
                        "\tat net.minecraft.server.v1_16_R3.World.lambda$new$0(World.java:196) ~[patched_1.16.5.jar:git-Paper-613]\n" +
                        "\tat java.util.Optional.orElseThrow(Optional.java:408) ~[?:?]\n" +
                        "\tat net.minecraft.server.v1_16_R3.World.<init>(World.java:195) ~[patched_1.16.5.jar:git-Paper-613]\n" +
                        "\tat net.minecraft.server.v1_16_R3.WorldServer.<init>(WorldServer.java:324) ~[patched_1.16.5.jar:git-Paper-613]\n" +
                        "\tat net.minecraft.server.v1_16_R3.MinecraftServer.loadWorld(MinecraftServer.java:557) ~[patched_1.16.5.jar:git-Paper-613]\n" +
                        "\tat net.minecraft.server.v1_16_R3.DedicatedServer.init(DedicatedServer.java:280) ~[patched_1.16.5.jar:git-Paper-613]\n" +
                        "\tat net.minecraft.server.v1_16_R3.MinecraftServer.w(MinecraftServer.java:1065) ~[patched_1.16.5.jar:git-Paper-613]\n" +
                        "\tat net.minecraft.server.v1_16_R3.MinecraftServer.lambda$a$0(MinecraftServer.java:289) ~[patched_1.16.5.jar:git-Paper-613]\n" +
                        "\tat java.lang.Thread.run(Thread.java:834) [?:?]",
                new StackTestOutput("java.lang.IllegalStateException", "Unregistered dimension type: net.minecraft.server.v1_16_R3.DimensionManager@15967d7e", 9));

        errors.put("[21:22:08] [Server thread/WARN]: java.lang.Exception\n" +
                        "[21:22:08] [Server thread/WARN]: \tat org.bukkit.craftbukkit.v1_16_R3.legacy.CraftLegacy.<clinit>(CraftLegacy.java:259)\n" +
                        "[21:22:08] [Server thread/WARN]: \tat org.bukkit.craftbukkit.v1_16_R3.util.CraftMagicNumbers.checkSupported(CraftMagicNumbers.java:369)\n" +
                        "[21:22:08] [Server thread/WARN]: \tat org.bukkit.plugin.java.JavaPluginLoader.loadPlugin(JavaPluginLoader.java:133)\n" +
                        "[21:22:08] [Server thread/WARN]: \tat org.bukkit.plugin.SimplePluginManager.loadPlugin(SimplePluginManager.java:397)\n" +
                        "[21:22:08] [Server thread/WARN]: \tat org.bukkit.plugin.SimplePluginManager.loadPlugins(SimplePluginManager.java:305)\n" +
                        "[21:22:08] [Server thread/WARN]: \tat org.bukkit.craftbukkit.v1_16_R3.CraftServer.loadPlugins(CraftServer.java:389)\n" +
                        "[21:22:08] [Server thread/WARN]: \tat net.minecraft.server.v1_16_R3.DedicatedServer.init(DedicatedServer.java:251)\n" +
                        "[21:22:08] [Server thread/WARN]: \tat net.minecraft.server.v1_16_R3.MinecraftServer.w(MinecraftServer.java:1065)\n" +
                        "[21:22:08] [Server thread/WARN]: \tat net.minecraft.server.v1_16_R3.MinecraftServer.lambda$a$0(MinecraftServer.java:289)\n" +
                        "[21:22:08] [Server thread/WARN]: \tat java.base/java.lang.Thread.run(Thread.java:834)",
                new StackTestOutput("java.lang.Exception", "", 10));
    }

    @Test
    void parse() {
        for (Map.Entry<String, StackTestOutput> entry : errors.entrySet()) {
            StackException output = Parser.parse(entry.getKey()).get(0);
            Assertions.assertEquals(entry.getValue(), output, "Parsing of error is incorrect");
        }
    }
}
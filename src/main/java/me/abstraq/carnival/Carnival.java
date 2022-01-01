/*
 * This file is part of Carnival.
 * Copyright 2021 abstraq <abstraq@outlook.com>
 *
 * Carnival is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * Carnival is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with Carnival.  If not, see <https://www.gnu.org/licenses/>.
 */
package me.abstraq.carnival;

import net.dv8tion.jda.api.GatewayEncoding;
import net.dv8tion.jda.api.JDA;
import net.dv8tion.jda.api.JDABuilder;
import net.dv8tion.jda.api.requests.GatewayIntent;
import net.dv8tion.jda.api.utils.MemberCachePolicy;
import org.jetbrains.annotations.NotNull;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.security.auth.login.LoginException;

public final class Carnival {
    private final Logger logger;

    private JDA api;

    public Carnival() {
        this.logger = LoggerFactory.getLogger(Carnival.class);
    }

    /**
     * Handle any initialization tasks for carnival. This method is run
     * whenever the bot is started.
     */
    public void initialize(final @NotNull String token) throws LoginException {
        this.logger.info("Starting the carnival...");

        //  Initialize JDA instance and login.
        this.api = JDABuilder.create(GatewayIntent.GUILD_MEMBERS, GatewayIntent.GUILD_BANS)
                .setMemberCachePolicy(MemberCachePolicy.ONLINE)
                .setGatewayEncoding(GatewayEncoding.ETF)
                .setRelativeRateLimit(false)
                .setToken(token)
                .build();

        this.logger.info("The carnival has began!");
    }

    /**
     * The main logger instance for Carnival.
     * @return the {@link Logger} instance.
     */
    public Logger logger() {
        return this.logger;
    }

    /**
     * The JDA instance for Carnival.
     * @return the {@link JDA} instance.
     */
    public JDA api() {
        return this.api;
    }

    /**
     * Entry point for carnival. Attempts to read the token from the
     * environment variable 'CARNIVAL_DISCORD_TOKEN' and start the bot.
     *
     * @param args command line arguments passed to the program.
     */
    public static void main(String[] args) {
        final Carnival carnival = new Carnival();
        final String token = System.getenv("CARNIVAL_DISCORD_TOKEN");
        final String error = "Invalid discord token provided. Please obtain a valid bot token from the developer portal.";
        if(token == null || token.length() == 0) {
            carnival.logger().error(error);
            System.exit(1);
            return;
        }

        try {
            carnival.initialize(token);
        } catch (LoginException ex) {
            carnival.logger().error(error, ex);
            System.exit(1);
        }
    }
}

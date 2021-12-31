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

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public final class Carnival {
    private final Logger logger;

    public Carnival() {
        this.logger = LoggerFactory.getLogger(Carnival.class);
    }

    /**
     * Handle any initialization tasks for carnival. This method is run whenever the bot is started.
     */
    public void initialize() {
        this.logger.info("Starting the carnival...");
        this.logger.info("The carnival has began!");
    }

    public static void main(String[] args) {
        final Carnival carnival = new Carnival();
        carnival.initialize();
    }
}

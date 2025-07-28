/*
 * Copyright (c) 2014-2025 Wurst-Imperium and contributors.
 *
 * This source code is subject to the terms of the GNU General Public
 * License, version 3. If a copy of the GPL was not distributed with this
 * file, You can obtain one at: https://www.gnu.org/licenses/gpl-3.0.txt
 */
package net.wurstclient.commands;

import net.minecraft.client.MinecraftClient;
import net.minecraft.client.network.ClientPlayerEntity;
import net.wurstclient.DontBlock;
import net.wurstclient.command.CmdException;
import net.wurstclient.command.Command;
import net.wurstclient.util.ChatUtils;

@DontBlock
public class CopyCoordsCmd extends Command
{
	
	public CopyCoordsCmd()
	{
		super("copycoords", "Copy Coords to your clipboard", ".copycoords");
	}
	
	@Override
	public void call(String[] args) throws CmdException
	{
		ClientPlayerEntity player = MinecraftClient.getInstance().player;
		
		if(player == null)
			throw new CmdException()
			{
				
				@Override
				public void printToChat(Command cmd)
				{
					ChatUtils.message("§cPlayer not found.");
					
				}
			};
		
		double[] playersCoords = getPlayersCoords(player);
		
		double xCoords = playersCoords[0];
		double yCoords = playersCoords[1];
		double zCoords = playersCoords[2];
		
		String copiedCoords =
			String.format("%.2f, %.2f, %.2f", xCoords, yCoords, zCoords);
		
		MinecraftClient.getInstance().keyboard.setClipboard(copiedCoords);
		
		ChatUtils.message(
			String.format("§7Copied: §fX: §a%.2f §fY: §a%.2f §fZ: §a%.2f",
				xCoords, yCoords, zCoords));
	}
	
	public double[] getPlayersCoords(ClientPlayerEntity player)
	{
		double xCoords = player.getX();
		double yCoords = player.getY();
		double zCoords = player.getZ();
		
		double[] coords = {xCoords, yCoords, zCoords};
		
		return coords;
	}
	
}

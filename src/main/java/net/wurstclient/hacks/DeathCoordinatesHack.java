/*
 * Copyright (c) 2014-2025 Wurst-Imperium and contributors.
 *
 * This source code is subject to the terms of the GNU General Public
 * License, version 3. If a copy of the GPL was not distributed with this
 * file, You can obtain one at: https://www.gnu.org/licenses/gpl-3.0.txt
 */
package net.wurstclient.hacks;

import net.minecraft.client.MinecraftClient;
import net.minecraft.client.network.ClientPlayerEntity;
import net.wurstclient.Category;
import net.wurstclient.SearchTags;
import net.wurstclient.events.DeathListener;
import net.wurstclient.hack.Hack;
import net.wurstclient.util.ChatUtils;

@SearchTags({"death", "coordinates", "deathcoords", "deathpos", "died",
	"location", "death location", "last death", "where I died", "position",
	"xyz", "save coords", "save death", "death save"})
public final class DeathCoordinatesHack extends Hack implements DeathListener
{
	
	public DeathCoordinatesHack()
	{
		super("DeathCoordinates");
		setCategory(Category.OTHER);
	}
	
	@Override
	protected void onEnable()
	{
		EVENTS.add(DeathListener.class, this);
	}
	
	@Override
	protected void onDisable()
	{
		EVENTS.remove(DeathListener.class, this);
	}
	
	@Override
	public void onDeath()
	{
		ClientPlayerEntity player = MinecraftClient.getInstance().player;
		
		if(player == null)
			return;
		
		double[] Coords = getPlayersCoords(player);
		
		double xCoords = Coords[0];
		double yCoords = Coords[1];
		double zCoords = Coords[2];
		
		ChatUtils.message(
			String.format("§7You Died At: §fX: §a%.2f §fY: §a%.2f §fZ: §a%.2f",
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

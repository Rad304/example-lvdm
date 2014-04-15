package net.gtaun.shoebill.example.lvdm;

import net.gtaun.shoebill.SampObjectManager;
import net.gtaun.shoebill.common.command.Command;
import net.gtaun.shoebill.data.Color;
import net.gtaun.shoebill.data.Location;
import net.gtaun.shoebill.object.Menu;
import net.gtaun.shoebill.object.Player;

public class LvdmCommands
{	
	public LvdmCommands()
	{
		
	}
	
	@Command
	boolean pickup(Player p)
	{
		Location location = p.getLocation();
		location.y += 10;
		SampObjectManager.get().createPickup(351, 15, location);
		return true;	
	}

	@Command
	boolean menu(Player p)
	{
		Menu menu = SampObjectManager.get().createMenu("test1", 1, 0, 0, 100, 100);
		menu.setColumnHeader(0, "test2");
		menu.addItem(0, "hi");
		menu.addItem(0, "hey");
		menu.show(p);
		
		return true;
	}

	@Command
	boolean checkpoint(Player p)
	{
		Location location = p.getLocation();
		location.setX(location.getX() + 10);
		//Checkpoint checkpoint = new Checkpoint(location, 5);
		//Checkpoint usingCheckpoint = p.getCheckpoint();
		//if (usingCheckpoint != null) checkpoints.remove(usingCheckpoint);
		//checkpoints.add(checkpoint);
		//player.setCheckpoint(checkpoint);
		
		return true;
	}

	@Command
	boolean tp(Player p)
	{
		p.sendMessage(Color.WHITE, "Usage: /tp [x] [y] [z]");
		return true;
	}

	@Command
	boolean tp(Player p, float x, float y, float z)
	{
		p.setLocation(x, y, z);
		return true;
	}

	@Command
	boolean world(Player p)
	{
		p.sendMessage(Color.WHITE, "Usage: /world [id]");
		return true;
	}

	@Command
	boolean world(Player p, int worldId)
	{
		p.setWorld(worldId);
		return true;
	}

	@Command
	boolean interior(Player p)
	{
		p.sendMessage(Color.WHITE, "Usage: /interior [id]");
		return true;	
	}

	@Command
	boolean interior(Player p, int interiorId)
	{
		p.setInterior(interiorId);
		return true;
	}

	@Command
	boolean angle(Player p)
	{
		p.sendMessage(Color.WHITE, "Usage: /angle [angle]");
		return true;
	}

	@Command
	boolean angle(Player p, float angle)
	{
		p.setAngle(angle);
		return true;
	}

	@Command
	boolean kill(Player p)
	{
		p.setHealth(0.0f);
		return true;
	}

	@Command
	boolean codepage(Player p)
	{
		p.sendMessage(Color.WHITE, "Usage: /codepage [code]");
		return true;
	}

	@Command
	boolean codepage(Player p, int code)
	{
		p.setCodepage(code);
		return true;
	}

	@Command
	boolean givecash(Player p)
	{
		p.sendMessage(Color.WHITE, "Usage: /givecash [playerid] [amount]");
		return true;
	}

	@Command
	boolean givecash(Player p, Player target, int amount)
	{
		if (target == null || target == p)
		{
			p.sendMessage(Color.RED, "Invalid player id.");
			return true;
		}
		
		if (amount <= 0 || amount > p.getMoney())
		{
			p.sendMessage(Color.WHITE, "Invalid transaction amount.");
			return true;
		}
		
		p.giveMoney(-amount);
		target.giveMoney(amount);
		
		p.sendMessage(Color.YELLOW, "You have sent " + target.getName() + "(" + target.getId() + "), $" + amount);
		target.sendMessage(Color.YELLOW, "You have recieved $" + amount + " from " + p.getName() + "(" + p.getId() + ").");
		
		LvdmGamemode.logger().info("{}({}) has transfered {} to {}({})\n", p.getName(), p.getId(), amount, target.getName(), target.getId());
		return true;
	}

	@Command
	boolean objective(Player p)
	{
		p.sendMessage(Color.YELLOW, "This gamemode is faily open, there's no specific win / endgame conditions to meet.");
		p.sendMessage(Color.YELLOW, "In LVDM:Money Grub, when you kill a player, you will receive whatever money they have.");
		p.sendMessage(Color.YELLOW, "Consequently, if you have lots of money, and you die, your killer gets your cash.");
		p.sendMessage(Color.YELLOW, "However, you're not forced to kill players for money, you can always gamble in the");
		p.sendMessage(Color.YELLOW, "Casino's.");
		
		return true;
	}

	@Command
	boolean tips(Player p)
	{
		p.sendMessage(Color.YELLOW, "Spawning with just a desert eagle might sound lame, however the idea of this");
		p.sendMessage(Color.YELLOW, "gamemode is to get some cash, get better guns, then go after whoever has the");
		p.sendMessage(Color.YELLOW, "most cash. Once you've got the most cash, the idea is to stay alive(with the");
		p.sendMessage(Color.YELLOW, "cash intact)until the game ends, simple right?");
		
		return true;
	}

	@Command
	boolean help(Player p)
	{
		p.sendMessage(Color.YELLOW, "Las Venturas Deathmatch: Money Grub Coded By Jax and the SA-MP Team.");
		p.sendMessage(Color.YELLOW, "Type: /objective : to find out what to do in this gamemode.");
		p.sendMessage(Color.YELLOW, "Type: /givecash [playerid] [money-amount] to send money to other players.");
		p.sendMessage(Color.YELLOW, "Type: /tips : to see some tips from the creator of the gamemode.");
		
		return true;
	}
}
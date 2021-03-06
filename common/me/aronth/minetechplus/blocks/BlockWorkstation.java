package me.aronth.minetechplus.blocks;

import me.aronth.minetechplus.MineTechPlus;
import me.aronth.minetechplus.blocks.tileentitys.TileCraftingTable;
import me.aronth.minetechplus.blocks.tileentitys.TileIdeaBuilder;
import me.aronth.minetechplus.blocks.tileentitys.TileWorkstation;
import me.aronth.minetechplus.core.BlockHandler;
import me.aronth.minetechplus.core.ConfigHandler;
import me.aronth.minetechplus.core.helpers.ItemStackHelper;
import me.aronth.minetechplus.lib.GuiIds;
import net.minecraft.block.material.Material;
import net.minecraft.client.renderer.texture.IconRegister;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

public class BlockWorkstation extends MTBlockContainer {
    
	public BlockWorkstation(int id, Material material) {
		super(id, material);
		this.setUnlocalizedName("blockWorkstation");
		this.setCreativeTab(MineTechPlus.tab);
	}

	/*public void breakBlock(World par1World, int par2, int par3, int par4, int par5, int par6){
	    //System.out.println(par2 + ":" + par3  + ":" + par4  + ":" + par5  + ":" + par6);
        super.breakBlock(par1World, par2, par3, par4, par5, par6);
        if(isBlockAModule(par1World.getBlockId(par2+1, par3, par4)))
            par1World.setBlockToAir(par2+1, par3, par4);
        if(isBlockAModule(par1World.getBlockId(par2-1, par3, par4)))
            par1World.setBlockToAir(par2-1, par3, par4);
        if(isBlockAModule(par1World.getBlockId(par2, par3, par4+1)))
            par1World.setBlockToAir(par2, par3, par4+1);
        if(isBlockAModule(par1World.getBlockId(par2, par3, par4-1)))
            par1World.setBlockToAir(par2, par3, par4-1);
    }*/

    @Override
    public void breakBlock(World w, int x, int y, int z, int par5, int par6) {
        TileEntity tile = w.getBlockTileEntity(x, y, z);
        ItemStackHelper.dropStack(((TileWorkstation) tile).getStackInSlot(0), w, x, y, z);
        super.breakBlock(w, x, y, z, par5, par6);
    }

	public boolean onBlockActivated(World world, int x, int y, int z, EntityPlayer me, int par6, float par7, float par8, float par9) {
	    if(me.getHeldItem() != null && isBlockAModule(me.getHeldItem())){
	        //System.out.println("IDAE BUILDER");
	        //TileWorkstation station = (TileWorkstation)world.getBlockTileEntity(x, y, z);
	        // station.
	        return false;
	    }
	    
	    if(!me.isSneaking()){
	        TileWorkstation station = (TileWorkstation)world.getBlockTileEntity(x, y, z);
	        if(!station.isRefining(me)){
	            me.openGui(MineTechPlus.instance, GuiIds.GUI_WORKSTATION, world, x, y, z);
	            return true;
	        }
	    }
		return false;
	}
	
	@Override
	@SideOnly(Side.CLIENT)
	public void registerIcons(IconRegister reg) {
		//this.blockIcon = reg.registerIcon(Reference.MOD_ID.toLowerCase() + ":" + this.getUnlocalizedName().substring(this.getUnlocalizedName().indexOf(".") + 1));
	    this.blockIcon = reg.registerIcon(this.getTexture());
	}
	
	public boolean shouldSideBeRendered(IBlockAccess par1IBlockAccess, int par2, int par3, int par4, int par5){
	    return true;
	}
	
	@Override
    public TileEntity createNewTileEntity(World world) {
        return new TileWorkstation();
    }

	@Override
	public boolean isOpaqueCube() {
		return false;
	}

	@Override
	public boolean renderAsNormalBlock() {
		return false;
	}

	@Override
	public int getRenderType() {
		return ConfigHandler.renderId;
	}

	public static boolean isBlockAModule(ItemStack stack){
	    if(stack.isItemEqual(new ItemStack(BlockHandler.blockIdeaBuilder)))
	        return true;
	    if(stack.isItemEqual(new ItemStack(BlockHandler.blockCraftingTable)))
            return true;
	    return false;
	}
	
	public static boolean isBlockAModule(int blockid){
        if(blockid == MineTechPlus.instance.config.IDIdeaBuilder)
            return true;
        if(blockid == MineTechPlus.instance.config.IDCraftingTable)
            return true;
        return false;
    }
	
	public static boolean isBlockAModule(TileEntity tile){
	    if(tile instanceof TileIdeaBuilder)
	        return true;
	    if(tile instanceof TileCraftingTable)
	        return true;
	    return false;
	}
	
}

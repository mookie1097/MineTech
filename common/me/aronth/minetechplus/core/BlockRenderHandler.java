package me.aronth.minetechplus.core;

import me.aronth.minetechplus.blocks.BlockWorkstation;
import net.minecraft.block.Block;
import net.minecraft.client.renderer.RenderBlocks;
import net.minecraft.client.renderer.Tessellator;
import net.minecraft.world.IBlockAccess;

import org.lwjgl.opengl.GL11;

import cpw.mods.fml.client.registry.ISimpleBlockRenderingHandler;
import cpw.mods.fml.client.registry.RenderingRegistry;

public class BlockRenderHandler implements ISimpleBlockRenderingHandler {

	@Override
	public void renderInventoryBlock(Block block, int metadata, int modelID, RenderBlocks renderer) {
		if(block instanceof BlockWorkstation){ // Workstation
			// Renders the table it self
			renderer.setRenderBounds(0, 0.7, 0, 1, 1, 1);
			renderDo(renderer, block, metadata);
			
			// All !! the feet of the table ! :D
			renderer.setRenderBounds(0.05, 0, 0.05, 0.3, 0.99, 0.3);
			renderDo(renderer, block, metadata);
			renderer.setRenderBounds(0.7, 0, 0.05, 0.95, 0.99, 0.3);
			renderDo(renderer, block, metadata);
			renderer.setRenderBounds(0.7, 0, 0.7, 0.95, 0.99, 0.95);
			renderDo(renderer, block, metadata);
			renderer.setRenderBounds(0.05, 0, 0.7, 0.3, 0.99, 0.95);
			renderDo(renderer, block, metadata);	
		}
	}

	@Override
	public boolean renderWorldBlock(IBlockAccess world, int x, int y, int z, Block block, int modelId, RenderBlocks renderer) {
		if(block instanceof BlockWorkstation)
			renderWorkstation(world, x, y, z, block, modelId, renderer);
		return false;
	}

	private void renderWorkstation(IBlockAccess world, int x, int y, int z, Block block, int modelId, RenderBlocks renderer) {
		// Renders the table it self
		renderer.setRenderBounds(0, 0.7, 0, 1, 1, 1);
		renderer.renderStandardBlock(block, x, y, z);
		
		// All !! the feet of the table ! :D
		renderer.setRenderBounds(0.05, 0, 0.05, 0.3, 0.99, 0.3);
		renderer.renderStandardBlock(block, x, y, z);
		renderer.setRenderBounds(0.7, 0, 0.05, 0.95, 0.99, 0.3);
		renderer.renderStandardBlock(block, x, y, z);
		renderer.setRenderBounds(0.7, 0, 0.7, 0.95, 0.99, 0.95);
		renderer.renderStandardBlock(block, x, y, z);
		renderer.setRenderBounds(0.05, 0, 0.7, 0.3, 0.99, 0.95);
		renderer.renderStandardBlock(block, x, y, z);	
	}

	@Override
	public boolean shouldRender3DInInventory() {
		return true;
	}

	private void renderDo(RenderBlocks renderblocks, Block block, int meta){
		Tessellator tessellator = Tessellator.instance;
		GL11.glTranslatef(-0.5F, -0.5F, -0.5F);
		tessellator.startDrawingQuads();
		tessellator.setNormal(0.0F, -1F, 0.0F);
		renderblocks.renderBottomFace(block, 0.0D, 0.0D, 0.0D, block.getBlockTextureFromSideAndMetadata(0, meta));
		tessellator.draw();
		tessellator.startDrawingQuads();
		tessellator.setNormal(0.0F, 1.0F, 0.0F);
		renderblocks.renderTopFace(block, 0.0D, 0.0D, 0.0D, block.getBlockTextureFromSideAndMetadata(1, meta));
		tessellator.draw();
		tessellator.startDrawingQuads();
		tessellator.setNormal(0.0F, 0.0F, -1F);
		renderblocks.renderEastFace(block, 0.0D, 0.0D, 0.0D, block.getBlockTextureFromSideAndMetadata(2, meta));
		tessellator.draw();
		tessellator.startDrawingQuads();
		tessellator.setNormal(0.0F, 0.0F, 1.0F);
		renderblocks.renderWestFace(block, 0.0D, 0.0D, 0.0D, block.getBlockTextureFromSideAndMetadata(3, meta));
		tessellator.draw();
		tessellator.startDrawingQuads();
		tessellator.setNormal(-1F, 0.0F, 0.0F);
		renderblocks.renderNorthFace(block, 0.0D, 0.0D, 0.0D, block.getBlockTextureFromSideAndMetadata(4, meta));
		tessellator.draw();
		tessellator.startDrawingQuads();
		tessellator.setNormal(1.0F, 0.0F, 0.0F);
		renderblocks.renderSouthFace(block, 0.0D, 0.0D, 0.0D, block.getBlockTextureFromSideAndMetadata(5, meta));
		tessellator.draw();
		GL11.glTranslatef(0.5F, 0.5F, 0.5F);
	}
	
	@Override
	public int getRenderId() {
		ConfigHandler.renderId = RenderingRegistry.getNextAvailableRenderId();
		return ConfigHandler.renderId;
	}

}

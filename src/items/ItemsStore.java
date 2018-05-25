package items;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RadialGradientPaint;
import java.awt.geom.Point2D;
import java.util.ArrayList;

import constants.Constants;
import ia.IAGenerator;
import juegoEspacio.GameHandler;

public class ItemsStore {
	int size = 100;
	int border = 10;
	int totalSize = 5;
	int rightBorder = 50;
	ArrayList<ImageItem> slots = new ArrayList<>();
	ArrayList<ImageItem> slotsToRemove = new ArrayList<>();

	boolean[] selected = { false, false, false, false, false };
	int[] progress = { 0, 0, 0, 0, 0 };
	int selectedBorder = 5;
	int espesorSelected = 2;
	boolean putMessageToGet;
	boolean putMessageToUse;
	int XmessageGet = 0;
	int YmessageGet = 0;

	public ItemsStore() {
		createBox(0);
		createBox(1);
		createBox(2);
		createBox(3);
		createBox(4);
	}

	public void render(Graphics g) {
		int startingX = GameHandler.getVentana().screenSize.width - totalSize * size - totalSize * border - rightBorder;
		int y = GameHandler.getVentana().screenSize.height - size - border;
		totalSize = 5;
		renderBox(0, g, startingX, y);
		renderBox(1, g, startingX, y);
		renderBox(2, g, startingX, y);
		renderBox(3, g, startingX, y);
		renderBox(4, g, startingX, y);
		renderMessages(g, startingX, y);
		for (ImageItem ii : slotsToRemove) {
			ii.resetImage();
		}
		slotsToRemove.removeAll(slots);
	}

	public void renderBox(int pos, Graphics g, int startingX, int y) {

		int startDepPos = startingX + pos * size + border * pos;
		if (isSelected(pos)) {
			y -= 10;
		}

		g.setFont(Constants.FONT);
		g.setColor(new Color(64, 64, 64, 124));
		g.fillRect(2 + startDepPos, y, size - 2, 2);
		g.fillRect(startDepPos, y + size, size + 2, 2);
		g.fillRect(startDepPos + size, y, 2, size);
		g.fillRect(startDepPos, y, 2, size);

		slots.get(pos).renderImage(g, startDepPos, y, size);
		g.setColor(Color.WHITE);
//		g.drawString(Integer.toString(pos + 1), startDepPos + 5, y + 20);

		if (selected[pos]) {
			renderSelected(g, startDepPos, y, pos);
			renderDisplayInfoMessage(g, pos);
		}
		

	}

	public void renderMessages(Graphics g, int startingX, int y) {
		g.setColor(Color.GRAY);
		if (putMessageToGet) {
			g.drawString("Press E to take", XmessageGet, YmessageGet);
		}
		if (putMessageToUse) {

			g.drawString("Press R to use    Press F to leave", startingX + 10, y - 20);
		}
	}

	public void renderSelected(Graphics g, int startDepPos, int y, int pos) {
		g.fillRect(startDepPos - selectedBorder, y - selectedBorder, size + selectedBorder * 2, espesorSelected);
		g.fillRect(startDepPos - selectedBorder, y + size + selectedBorder, size + selectedBorder * 2, espesorSelected);
		g.fillRect(startDepPos + size + selectedBorder, y - selectedBorder, espesorSelected, size + selectedBorder * 2);
		g.fillRect(startDepPos - selectedBorder, y - selectedBorder, espesorSelected, size + selectedBorder * 2);

	}
	public void renderDisplayInfoMessage(Graphics g,int pos) {
		if(slots.get(pos).i!=null) {
		int yInfo= GameHandler.getVentana().getPanelActual().getPanelLife().getY()-25;
		int xCenter=GameHandler.getVentana().screenSize.width/2;
		g.drawImage(slots.get(pos).getItem().getInitialImage(),xCenter+5,yInfo-30 , 40, 40, null);
		g.setFont(Constants.FONT);
		g.setColor(new Color(125,125,125,150));
		g.drawString(Integer.toString(slots.get(pos).getQuantity()), xCenter-10, yInfo);
		}
	}

	public void createBox(int pos) {
		int startingX = GameHandler.getVentana().screenSize.width - totalSize * size - totalSize * border - rightBorder;
		int y = GameHandler.getVentana().screenSize.height - size - border;
		int startDepPos = startingX + pos * size + border * pos;
		slots.add(new ImageItem(startDepPos, y, pos, size));
	}

	public boolean isEmpty(int pos) {
		if (slots.get(pos).getI() != null) {
			return false;
		} else {
			return true;
		}
	}

	public boolean isthereItemInProgress() {
		for (ImageItem ii : getSlots()) {
			if (ii.counting) {
				return true;
			}
		}
		return false;
	}

	public void putMessageToGet(boolean action, int x, int y) {
		
		putMessageToGet = action;
		XmessageGet = x;
		YmessageGet = y;
	}

	public void putMessageToUse(boolean action) {
		putMessageToUse = action;
	}

	public void leaveItem() {
		ImageItem i = GameHandler.getVentana().getPanelActual().getItemStore().getSelected();
		if (!GameHandler.getVentana().getPanelActual().getItemStore().allUnselected()
				&& !GameHandler.getVentana().getPanelActual().getItemStore().isEmpty(i.getPos())) {

			Item item = i.getItem();
			item.setQuantity(i.getQuantity());
			item.changeX(IAGenerator.mainPlayer.getX() + IAGenerator.mainPlayer.realXPlayerCoordinate);
			item.changeY(IAGenerator.mainPlayer.getY() + IAGenerator.mainPlayer.realYPlayerCoordinate);
			item.setInStore(false);

			GameHandler.getVentana().getPanelActual().getItemsHandler().restoreItem(item);

		}

	}

	public void leaveItemAndReset() {
		ImageItem i = GameHandler.getVentana().getPanelActual().getItemStore().getSelected();
		if (!GameHandler.getVentana().getPanelActual().getItemStore().allUnselected()
				&& !GameHandler.getVentana().getPanelActual().getItemStore().isEmpty(i.getPos())) {

			Item item = i.getItem();
			item.setQuantity(i.getQuantity());
			i.setQuantity(0);

			item.changeX(IAGenerator.mainPlayer.getX() + IAGenerator.mainPlayer.realXPlayerCoordinate);
			item.changeY(IAGenerator.mainPlayer.getY() + IAGenerator.mainPlayer.realYPlayerCoordinate);
			item.setInStore(false);
			GameHandler.getVentana().getPanelActual().getItemsHandler().leavedItem();

			GameHandler.getVentana().getPanelActual().getItemsHandler().restoreItem(item);
			GameHandler.getVentana().getPanelActual().getItemStore().getSlotsToRemove().add(i);
		}

	}

	public void updateSlot(Item i, int pos) {
		slots.get(pos).setI(i.getInitialImage(), i.getId(), i);
		slots.get(pos).addQuantity(i.getQuantity());
		
		GameHandler.getVentana().getPanelActual().getItemsHandler().getItemsToRemove().add(i);
		if (getPosSelected() == pos) {
			putMessageToUse(true);
		}
	}

	public void addItem(Item i) {
		i.setInStore(true);

		if (isEmpty(0)) {
			updateSlot(i, 0);
		} else if (InfoStore.getItems().get(i.getId()).getName()
				.equals(InfoStore.getItems().get(slots.get(0).getItem().getId()).getName())
				&& slots.get(0).getQuantity() +i.getQuantity() <= InfoStore.getItems().get(slots.get(0).getItem().getId())
						.getMaxStacks()) {
			
				updateSlot(i, 0);
			

		} else if (isEmpty(1)) {
			updateSlot(i, 1);
		} else if (InfoStore.getItems().get(i.getId()).getName()
				.equals(InfoStore.getItems().get(slots.get(1).getItem().getId()).getName())
				&& slots.get(1).getQuantity()+i.getQuantity() <= InfoStore.getItems().get(slots.get(1).getItem().getId())
						.getMaxStacks()) {
			
				updateSlot(i, 1);
			


		} else if (isEmpty(2)) {
			updateSlot(i, 2);
		} else if (InfoStore.getItems().get(i.getId()).getName()
				.equals(InfoStore.getItems().get(slots.get(2).getItem().getId()).getName())
				&& slots.get(2).getQuantity() +i.getQuantity() <= InfoStore.getItems().get(slots.get(2).getItem().getId())
						.getMaxStacks()) {
			
				updateSlot(i, 2);
			


		} else if (isEmpty(3)) {
			updateSlot(i, 3);
		} else if (InfoStore.getItems().get(i.getId()).getName()
				.equals(InfoStore.getItems().get(slots.get(3).getItem().getId()).getName())
				&& slots.get(3).getQuantity() +i.getQuantity() <= InfoStore.getItems().get(slots.get(3).getItem().getId())
						.getMaxStacks()) {
			
				updateSlot(i, 3);
			


		} else if (isEmpty(4)) {
			updateSlot(i, 4);
		} else if (InfoStore.getItems().get(i.getId()).getName()
				.equals(InfoStore.getItems().get(slots.get(4).getItem().getId()).getName())
				&& slots.get(4).getQuantity() +i.getQuantity() <= InfoStore.getItems().get(slots.get(4).getItem().getId())
						.getMaxStacks()) {
		
				updateSlot(i, 4);
			


		} else {
			if (getPosSelected() != -1) {
				
				GameHandler.getVentana().getPanelActual().getItemStore().leaveItem();
				slots.get(getPosSelected()).setQuantity(0);
				slots.get(getPosSelected()).setI(i.getInitialImage(), i.getId(), i);
				slots.get(getPosSelected()).addQuantity(i.getQuantity());
				GameHandler.getVentana().getPanelActual().getItemsHandler().getItemsToRemove().add(i);
				putMessageToUse(true);
				
			} else {
				i.setInStore(false);
				GameHandler.getVentana().getPanelActual().changeMessage("There is no slot selected");
			}
		}

	}

	public boolean allUnselected() {
		int c = 0;
		for (int i = 0; i < selected.length; i++) {
			if (selected[i]) {

				c++;
			}
		}
		if (c == 0) {
			return true;
		}
		return false;
	}

	public int getPosSelected() {
		for (int i = 0; i < selected.length; i++) {
			if (selected[i]) {

				return i;
			}
		}
		return -1;

	}

	public ImageItem getSelected() {
		for (int i = 0; i < selected.length; i++) {
			if (selected[i]) {

				return slots.get(i);
			}
		}
		return null;

	}

	public boolean isSelected(int pos) {
		return selected[pos];
	}

	public void setSelected(boolean selected, int pos) {
		this.selected[pos] = selected;
	}

	public ArrayList<ImageItem> getSlots() {
		return slots;
	}

	public ImageItem getSlot(int pos) {
		return slots.get(pos);
	}

	public ArrayList<ImageItem> getSlotsToRemove() {
		return slotsToRemove;
	}

}

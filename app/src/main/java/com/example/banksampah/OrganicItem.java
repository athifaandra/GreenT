package com.example.banksampah;

public class OrganicItem {

    private int imageResource;
    private String trashName;
    private boolean checked;

    public OrganicItem() {
        // Default constructor required for DataSnapshot.getValue(OrganicItem.class)
    }

    public OrganicItem(int imageResource, String trashName, boolean checked) {
        this.imageResource = imageResource;
        this.trashName = trashName;
        this.checked = checked;
    }

    public int getImageResource() {
        return imageResource;
    }

    public void setImageResource(int imageResource) {
        this.imageResource = imageResource;
    }

    public String getTrashName() {
        return trashName;
    }

    public void setTrashName(String trashName) {
        this.trashName = trashName;
    }

    public boolean isChecked() {
        return checked;
    }

    public void setChecked(boolean checked) {
        this.checked = checked;
    }
}
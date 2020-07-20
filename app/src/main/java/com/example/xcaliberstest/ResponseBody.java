package com.example.xcaliberstest;
import java.util.List;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class ResponseBody {
    @SerializedName("totalItems")
    @Expose
    private Integer totalItems;
    @SerializedName("endIndex")
    @Expose
    private Integer endIndex;
    @SerializedName("startIndex")
    @Expose
    private Integer startIndex;
    @SerializedName("itemsPerPage")
    @Expose
    private Integer itemsPerPage;
    @SerializedName("items")
    @Expose
    private List<Item> items = null;

    public Integer getTotalItems() {
        return totalItems;
    }

    public void setTotalItems(Integer totalItems) {
        this.totalItems = totalItems;
    }

    public Integer getEndIndex() {
        return endIndex;
    }

    public void setEndIndex(Integer endIndex) {
        this.endIndex = endIndex;
    }

    public Integer getStartIndex() {
        return startIndex;
    }

    public void setStartIndex(Integer startIndex) {
        this.startIndex = startIndex;
    }

    public Integer getItemsPerPage() {
        return itemsPerPage;
    }

    public void setItemsPerPage(Integer itemsPerPage) {
        this.itemsPerPage = itemsPerPage;
    }

    public List<Item> getItems() {
        return items;
    }

    public void setItems(List<Item> items) {
        this.items = items;
    }

}

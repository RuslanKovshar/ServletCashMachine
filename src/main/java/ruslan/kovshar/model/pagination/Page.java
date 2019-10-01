package ruslan.kovshar.model.pagination;

import java.util.ArrayList;
import java.util.List;

/**
 * contains the page info
 *
 * @param <T> object that page will be contain
 */
public class Page<T> {

    private int totalRecords;
    private int currentPage = 1;
    private List<T> content = new ArrayList<>();
    private int maxResult = 12;
    private int totalPages;
    private String sortType = "DESC";

    public Page() {
    }

    public Page(Integer currentPage, Integer maxResult, String sortType) {
        this.currentPage = currentPage == null ? this.currentPage : currentPage;
        this.maxResult = maxResult == null ? this.maxResult : maxResult;
        this.sortType = sortType == null ? this.sortType : sortType;
    }

    public int getTotalRecords() {
        return totalRecords;
    }

    public void setTotalRecords(int totalRecords) {
        this.totalRecords = totalRecords;
    }

    public int getCurrentPage() {
        return currentPage;
    }

    public void setCurrentPage(int currentPage) {
        this.currentPage = currentPage;
    }

    public List<T> getContent() {
        return content;
    }

    public void setContent(List<T> content) {
        this.content = content;
    }

    public int getMaxResult() {
        return maxResult;
    }

    public void setMaxResult(int maxResult) {
        this.maxResult = maxResult;
    }

    public int getTotalPages() {
        return totalPages;
    }

    public void setTotalPages(int totalPages) {
        this.totalPages = totalPages;
    }

    public String getSortType() {
        return sortType;
    }

    public void setSortType(String sortType) {
        this.sortType = sortType;
    }
}

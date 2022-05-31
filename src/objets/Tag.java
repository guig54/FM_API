package objets;

public class Tag {
	
	private String name, summary, content;
	private int total, reach;
	
	public Tag(String name,int total,int reach, String summary, String content) {
		this.name = name;
		this.summary = summary;
		this.content = content;
		this.total = total;
		this.reach = reach;
	}

	@Override
	public String toString() {
		return "Tag [name=" + name + ", summary=" + summary + ", content=" + content + ", total=" + total + ", reach="
				+ reach + "]";
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getSummary() {
		return summary;
	}

	public void setSummary(String summary) {
		this.summary = summary;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public int getTotal() {
		return total;
	}

	public void setTotal(int total) {
		this.total = total;
	}

	public int getReach() {
		return reach;
	}

	public void setReach(int reach) {
		this.reach = reach;
	}



}

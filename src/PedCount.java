import java.time.LocalDateTime;
import java.util.Objects;

public class PedCount {
    private LocalDateTime dateTime;
    private String site;
    private int totalCount;
    private int countToManhattan;
    private int countToBrooklyn;
    private String weatherSummary;
    private double temperatureF;
    private double precipitationInches;
    private double latitude;
    private double longitude;
    private boolean isHoliday;

    public PedCount(LocalDateTime dateTime, String site, int totalCount, int countToManhattan,
                    int countToBrooklyn, String weatherSummary, double temperatureF,
                    double precipitationInches, double latitude, double longitude, boolean isHoliday) {
        this.dateTime = dateTime;
        this.site = site;
        this.totalCount = totalCount;
        this.countToManhattan = countToManhattan;
        this.countToBrooklyn = countToBrooklyn;
        this.weatherSummary = weatherSummary;
        this.temperatureF = temperatureF;
        this.precipitationInches = precipitationInches;
        this.latitude = latitude;
        this.longitude = longitude;
        this.isHoliday = isHoliday;
    }

	public LocalDateTime getDateTime() {
		return dateTime;
	}

	public void setDateTime(LocalDateTime dateTime) {
		this.dateTime = dateTime;
	}

	public String getSite() {
		return site;
	}

	public void setSite(String site) {
		this.site = site;
	}

	public int getTotalCount() {
		return totalCount;
	}

	public void setTotalCount(int totalCount) {
		this.totalCount = totalCount;
	}

	public int getCountToManhattan() {
		return countToManhattan;
	}

	public void setCountToManhattan(int countToManhattan) {
		this.countToManhattan = countToManhattan;
	}

	public int getCountToBrooklyn() {
		return countToBrooklyn;
	}

	public void setCountToBrooklyn(int countToBrooklyn) {
		this.countToBrooklyn = countToBrooklyn;
	}

	public String getWeatherSummary() {
		return weatherSummary;
	}

	public void setWeatherSummary(String weatherSummary) {
		this.weatherSummary = weatherSummary;
	}

	public double getTemperatureF() {
		return temperatureF;
	}

	public void setTemperatureF(double temperatureF) {
		this.temperatureF = temperatureF;
	}

	public double getPrecipitationInches() {
		return precipitationInches;
	}

	public void setPrecipitationInches(double precipitationInches) {
		this.precipitationInches = precipitationInches;
	}

	public double getLatitude() {
		return latitude;
	}

	public void setLatitude(double latitude) {
		this.latitude = latitude;
	}

	public double getLongitude() {
		return longitude;
	}

	public void setLongitude(double longitude) {
		this.longitude = longitude;
	}

	public boolean isHoliday() {
		return isHoliday;
	}

	public void setHoliday(boolean isHoliday) {
		this.isHoliday = isHoliday;
	}
	
	

	@Override
	public String toString() {
		return "PedCount [dateTime=" + dateTime + ", site=" + site + ", totalCount=" + totalCount
				+ ", countToManhattan=" + countToManhattan + ", countToBrooklyn=" + countToBrooklyn
				+ ", weatherSummary=" + weatherSummary + ", temperatureF=" + temperatureF + ", precipitationInches="
				+ precipitationInches + ", latitude=" + latitude + ", longitude=" + longitude + ", isHoliday="
				+ isHoliday + "]";
	}

	@Override
	public int hashCode() {
		return Objects.hash(countToBrooklyn, countToManhattan, dateTime, isHoliday, latitude, longitude,
				precipitationInches, site, temperatureF, totalCount, weatherSummary);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		PedCount other = (PedCount) obj;
		return countToBrooklyn == other.countToBrooklyn && countToManhattan == other.countToManhattan
				&& Objects.equals(dateTime, other.dateTime) && isHoliday == other.isHoliday
				&& Double.doubleToLongBits(latitude) == Double.doubleToLongBits(other.latitude)
				&& Double.doubleToLongBits(longitude) == Double.doubleToLongBits(other.longitude)
				&& Double.doubleToLongBits(precipitationInches) == Double.doubleToLongBits(other.precipitationInches)
				&& Objects.equals(site, other.site)
				&& Double.doubleToLongBits(temperatureF) == Double.doubleToLongBits(other.temperatureF)
				&& totalCount == other.totalCount && Objects.equals(weatherSummary, other.weatherSummary);
	}


}

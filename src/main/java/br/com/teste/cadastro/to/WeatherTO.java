package br.com.teste.cadastro.to;

import br.com.teste.cadastro.util.JsonLocalDateTimeDeserializer;
import br.com.teste.cadastro.util.JsonLocalDateTimeSerializer;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.List;

/**
 * Classe que representa o clima.
 */
public class WeatherTO implements Serializable {

	private List<ConsolidatedWeatherTO> consolidated_weather;

	@JsonDeserialize(using = JsonLocalDateTimeDeserializer.class)
	@JsonSerialize(using = JsonLocalDateTimeSerializer.class)
	private LocalDateTime time;

	@JsonDeserialize(using = JsonLocalDateTimeDeserializer.class)
	@JsonSerialize(using = JsonLocalDateTimeSerializer.class)
	private LocalDateTime sun_rise;

	@JsonDeserialize(using = JsonLocalDateTimeDeserializer.class)
	@JsonSerialize(using = JsonLocalDateTimeSerializer.class)
	private LocalDateTime sun_set;

	private String timezone_name;
	private LocalizacaoTO parent;
	private List<SourceTO> sources;
	private String title;
	private String location_type;
	private Long woeid;
	private String latt_long;
	private String timezone;

	/**
	 * Construtor da classe sem parâmetros.
	 */
	public WeatherTO() {}

	/**
	 * Construtor da classe com parâmetros.
	 *
	 * @param consolidated_weather
	 * @param time
	 * @param sun_rise
	 * @param sun_set
	 * @param timezone_name
	 * @param parent
	 * @param sources
	 * @param title
	 * @param location_type
	 * @param woeid
	 * @param latt_long
	 * @param timezone
	 */
	public WeatherTO(List<ConsolidatedWeatherTO> consolidated_weather, LocalDateTime time, LocalDateTime sun_rise, LocalDateTime sun_set, String timezone_name, LocalizacaoTO parent, List<SourceTO> sources, String title, String location_type, Long woeid, String latt_long, String timezone) {
		this.consolidated_weather = consolidated_weather;
		this.time = time;
		this.sun_rise = sun_rise;
		this.sun_set = sun_set;
		this.timezone_name = timezone_name;
		this.parent = parent;
		this.sources = sources;
		this.title = title;
		this.location_type = location_type;
		this.woeid = woeid;
		this.latt_long = latt_long;
		this.timezone = timezone;
	}

	public List<ConsolidatedWeatherTO> getConsolidated_weather() {
		return consolidated_weather;
	}

	public void setConsolidated_weather(List<ConsolidatedWeatherTO> consolidated_weather) {
		this.consolidated_weather = consolidated_weather;
	}

	public LocalDateTime getTime() {
		return time;
	}

	public void setTime(LocalDateTime time) {
		this.time = time;
	}

	public LocalDateTime getSun_rise() {
		return sun_rise;
	}

	public void setSun_rise(LocalDateTime sun_rise) {
		this.sun_rise = sun_rise;
	}

	public LocalDateTime getSun_set() {
		return sun_set;
	}

	public void setSun_set(LocalDateTime sun_set) {
		this.sun_set = sun_set;
	}

	public String getTimezone_name() {
		return timezone_name;
	}

	public void setTimezone_name(String timezone_name) {
		this.timezone_name = timezone_name;
	}

	public LocalizacaoTO getParent() {
		return parent;
	}

	public void setParent(LocalizacaoTO parent) {
		this.parent = parent;
	}

	public List<SourceTO> getSources() {
		return sources;
	}

	public void setSources(List<SourceTO> sources) {
		this.sources = sources;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getLocation_type() {
		return location_type;
	}

	public void setLocation_type(String location_type) {
		this.location_type = location_type;
	}

	public Long getWoeid() {
		return woeid;
	}

	public void setWoeid(Long woeid) {
		this.woeid = woeid;
	}

	public String getLatt_long() {
		return latt_long;
	}

	public void setLatt_long(String latt_long) {
		this.latt_long = latt_long;
	}

	public String getTimezone() {
		return timezone;
	}

	public void setTimezone(String timezone) {
		this.timezone = timezone;
	}
}

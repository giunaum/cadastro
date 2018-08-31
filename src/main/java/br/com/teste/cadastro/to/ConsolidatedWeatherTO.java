package br.com.teste.cadastro.to;

import br.com.teste.cadastro.util.JsonLocalDateDeserializer;
import br.com.teste.cadastro.util.JsonLocalDateSerializer;
import br.com.teste.cadastro.util.JsonLocalDateTimeDeserializer;
import br.com.teste.cadastro.util.JsonLocalDateTimeSerializer;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;

import java.io.Serializable;
import java.time.LocalDate;
import java.time.LocalDateTime;

/**
 * Classe que representa o clima detalhado.
 */
public class ConsolidatedWeatherTO implements Serializable {

	private Long id;
	private String weather_state_name;
	private String weather_state_abbr;
	private String wind_direction_compass;

	@JsonDeserialize(using = JsonLocalDateTimeDeserializer.class)
	@JsonSerialize(using = JsonLocalDateTimeSerializer.class)
	private LocalDateTime created;

	@JsonDeserialize(using = JsonLocalDateDeserializer.class)
	@JsonSerialize(using = JsonLocalDateSerializer.class)
	private LocalDate applicable_date;

	private Double min_temp;
	private Double max_temp;
	private Double the_temp;
	private Double wind_speed;
	private Double wind_direction;
	private Double air_pressure;
	private Integer humidity;
	private Double visibility;
	private Integer predictability;

	/**
	 * Construtor da classe sem parâmetros.
	 */
	public ConsolidatedWeatherTO() {}

	/**
	 * Construtor da classe com parâmetros.
	 *
	 * @param id
	 * @param weather_state_name
	 * @param weather_state_abbr
	 * @param wind_direction_compass
	 * @param created
	 * @param applicable_date
	 * @param min_temp
	 * @param max_temp
	 * @param the_temp
	 * @param wind_speed
	 * @param wind_direction
	 * @param air_pressure
	 * @param humidity
	 * @param visibility
	 * @param predictability
	 */
	public ConsolidatedWeatherTO(Long id, String weather_state_name, String weather_state_abbr, String wind_direction_compass, LocalDateTime created, LocalDate applicable_date, Double min_temp, Double max_temp, Double the_temp, Double wind_speed, Double wind_direction, Double air_pressure, Integer humidity, Double visibility, Integer predictability) {
		this.id = id;
		this.weather_state_name = weather_state_name;
		this.weather_state_abbr = weather_state_abbr;
		this.wind_direction_compass = wind_direction_compass;
		this.created = created;
		this.applicable_date = applicable_date;
		this.min_temp = min_temp;
		this.max_temp = max_temp;
		this.the_temp = the_temp;
		this.wind_speed = wind_speed;
		this.wind_direction = wind_direction;
		this.air_pressure = air_pressure;
		this.humidity = humidity;
		this.visibility = visibility;
		this.predictability = predictability;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getWeather_state_name() {
		return weather_state_name;
	}

	public void setWeather_state_name(String weather_state_name) {
		this.weather_state_name = weather_state_name;
	}

	public String getWeather_state_abbr() {
		return weather_state_abbr;
	}

	public void setWeather_state_abbr(String weather_state_abbr) {
		this.weather_state_abbr = weather_state_abbr;
	}

	public String getWind_direction_compass() {
		return wind_direction_compass;
	}

	public void setWind_direction_compass(String wind_direction_compass) {
		this.wind_direction_compass = wind_direction_compass;
	}

	public LocalDateTime getCreated() {
		return created;
	}

	public void setCreated(LocalDateTime created) {
		this.created = created;
	}

	public LocalDate getApplicable_date() {
		return applicable_date;
	}

	public void setApplicable_date(LocalDate applicable_date) {
		this.applicable_date = applicable_date;
	}

	public Double getMin_temp() {
		return min_temp;
	}

	public void setMin_temp(Double min_temp) {
		this.min_temp = min_temp;
	}

	public Double getMax_temp() {
		return max_temp;
	}

	public void setMax_temp(Double max_temp) {
		this.max_temp = max_temp;
	}

	public Double getThe_temp() {
		return the_temp;
	}

	public void setThe_temp(Double the_temp) {
		this.the_temp = the_temp;
	}

	public Double getWind_speed() {
		return wind_speed;
	}

	public void setWind_speed(Double wind_speed) {
		this.wind_speed = wind_speed;
	}

	public Double getWind_direction() {
		return wind_direction;
	}

	public void setWind_direction(Double wind_direction) {
		this.wind_direction = wind_direction;
	}

	public Double getAir_pressure() {
		return air_pressure;
	}

	public void setAir_pressure(Double air_pressure) {
		this.air_pressure = air_pressure;
	}

	public Integer getHumidity() {
		return humidity;
	}

	public void setHumidity(Integer humidity) {
		this.humidity = humidity;
	}

	public Double getVisibility() {
		return visibility;
	}

	public void setVisibility(Double visibility) {
		this.visibility = visibility;
	}

	public Integer getPredictability() {
		return predictability;
	}

	public void setPredictability(Integer predictability) {
		this.predictability = predictability;
	}
}

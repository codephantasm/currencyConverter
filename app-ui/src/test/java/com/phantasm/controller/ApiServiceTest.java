package com.phantasm.controller;

import com.phantasm.entity.Currencies;
import org.assertj.core.api.SoftAssertions;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.testng.annotations.Test;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import static org.assertj.core.api.Assertions.assertThat;

/**
 * Unit test suite for {@link ApiService}.
 *
 * @author Raghav Sharma {raghav.sharma_1995@outlook.com}
 */
public class ApiServiceTest {

    private final AnnotationConfigApplicationContext appContext = new AnnotationConfigApplicationContext(ApiService.class);

    private final ApiService api = appContext.getBean(ApiService.class);

    private static final String SELECT_ALL_RECORDS_QUERY = "SELECT * FROM phantasm.\"Currencies\"";

    private static final String TEST_NAME = "TEST";
    private static final String TEST_ABBREV = "TST";
    private static final Double TEST_RATE = 1.097;

    @Test
    public void connect_test() {
        assertThat(api.connect()).isNotNull();
    }

    @Test
    public void connect_withExistingRecord_verifyRecord() throws SQLException {
        final Connection conn = api.connect();

        final Statement stmt = conn.createStatement();

        final ResultSet rs = stmt.executeQuery(SELECT_ALL_RECORDS_QUERY);

        rs.next();

        SoftAssertions softly = new SoftAssertions();
        softly.assertThat(rs.getString("Abbrev")).as("Column: Abbrev").isEqualTo(TEST_ABBREV);
        softly.assertThat(rs.getString("Name")).as("Column: Name").isEqualTo(TEST_NAME);
        softly.assertThat(rs.getDouble("Rate")).as("Column: Rate").isEqualTo(TEST_RATE);
        softly.assertAll();
    }

    @Test
    public void getRates_withDefaultConfig_verifyCurrenciesPOJO() {
        final Currencies currencies = ApiService.getRates();

        System.out.println(currencies.toString());

        System.out.println(currencies.getRates().get("USD"));
    }
}
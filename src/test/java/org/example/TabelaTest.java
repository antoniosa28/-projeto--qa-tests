package org.example;

import io.github.bonigarcia.wdm.WebDriverManager;
import io.qameta.allure.*;
import org.junit.jupiter.api.*;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import java.time.Duration;
import java.util.List;

public class TabelaTest {

    private WebDriver driver;

    @BeforeEach
    public void setup() {
        // Configura o driver do Chrome automaticamente
        WebDriverManager.chromedriver().setup();
        driver = new ChromeDriver();

        // Define um tempo de espera implícito (evita erros se o site demorar a carregar)
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));

        // Maximiza a janela
        driver.manage().window().maximize();
    }

    @Test
    public void testarClassificacaoEspn() {
        // 1. Navega para a página da ESPN premier league
        driver.get("https://www.espn.com.br/futebol/classificacao/_/liga/eng.1");

        System.out.println("Lendo dados da ESPN...");

        // Pega os nomes dos times
        List<WebElement> equipes = driver.findElements(By.className("Table__TD"));
        List<WebElement> pontos = driver.findElements(By.className("stat-cell"));

        System.out.println("\n--- 10º Primeiros colocados da Premier League ---");

        // Mostra os 10 primeiros da premier league
        for (int i = 0; i < 10; i++) {
            if (i < equipes.size()) {
                String nomeEquipe = equipes.get(i).getText();

                int indicePontos = (i * 8) + 7;
                String pontuacao = pontos.get(indicePontos).getText();

                System.out.println((i + 1) + "º Lugar: " + nomeEquipe + " - Pontos: " + pontuacao);
            }
        }
    }


    @AfterEach
    public void tearDown() {
        // Fecha o navegador ao terminar o teste
        if (driver != null) {
            driver.quit();
        }
    }
}
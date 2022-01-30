package acceptance;

        import cucumber.api.CucumberOptions;
        import cucumber.api.junit.Cucumber;
        import org.junit.runner.RunWith;

        /** Acceptance Test */
        @RunWith(Cucumber.class)
        //@CucumberOptions(features = 'classpath:feature')
        @CucumberOptions(dryRun = false, strict = true, features = "src/test/resources/feature", glue = "com.gradle.featuretests",monochrome = true)
        public class AcceptanceTest { }

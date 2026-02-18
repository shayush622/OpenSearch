package org.opensearch.index.analysis;

import org.opensearch.env.Environment;

import java.nio.file.Path;
import java.util.Arrays;
import java.util.List;

/**
 * Utils class to contain common functions under Analysis
 *
*/

public class Utils {

    private static final List<String> VALID_SUB_DIRECTORIES = Arrays.asList(new String[] { "analyzers", "hunspell" });

    public static Path resolveAnalyzerPath(Environment env, String analyzerPath) {
        Path normalizedAnalyzerPath = env.configDir().resolve(analyzerPath).normalize();
        for (String subDirectory : VALID_SUB_DIRECTORIES) {
            if (normalizedAnalyzerPath.startsWith(env.configDir().resolve(subDirectory).toAbsolutePath())) {
                return normalizedAnalyzerPath;
            }
        }
        throw new IllegalArgumentException(String.format("Invalid path [%s]", analyzerPath));
    }

}

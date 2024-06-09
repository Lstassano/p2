public class HTMLUtil {
    public static String escapeHTML(String input) {
        if (input == null) {
            return null;
        }
        return input.replaceAll("&", "&amp;")
                    .replaceAll("<", "&lt;")
                    .replaceAll(">", "&gt;")
                    .replaceAll("\"", "&quot;");
    }
}

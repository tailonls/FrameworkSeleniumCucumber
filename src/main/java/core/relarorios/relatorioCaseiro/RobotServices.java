package br.com.unicred.caixa.core;

import br.com.unicred.caixa.servicos.annotations.ColumnName;
import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.springframework.util.StringUtils;
import winium.elements.desktop.ComboBox;
import winium.elements.desktop.DataGrid;
import winium.elements.desktop.ListBox;

import java.awt.*;
import java.awt.event.KeyEvent;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.*;

import static br.com.unicred.caixa.core.WiniumFactory.getWiniumDriver;
import static br.com.unicred.caixa.core.report.GeradorReporteHTML.logFail;
import static br.com.unicred.caixa.core.report.GeradorReporteHTML.logPrintFail;
import static java.awt.event.KeyEvent.*;

public class RobotServices {
    private Robot robot;

    public RobotServices() {
        try {
            robot = new Robot();
        } catch (AWTException e) {
            e.printStackTrace();
        }
    }

    public void digitar(CharSequence characters) {
        try {
            int length = characters.length();
            for (int i = 0; i < length; i++) {
                char character = characters.charAt(i);
                type(character);
            }
        } catch (Exception e) {
            logFail("Erro ao informar caracteres [" + characters + "]");
            Assert.fail();
        }
    }

    public void pressKey(int keyEvent, Integer r) {
        for (int i = 0; i < r; i++) {
            doType(keyEvent);
        }
    }

    public void digitar(int... keyEvent) {
        doType(keyEvent);
    }

    public void digitarFuncao(String character) {
        switch (character) {
            case "F1": doType(VK_F1); break;
            case "F2": doType(VK_F2); break;
            case "F3": doType(VK_F3); break;
            case "F4": doType(VK_F4); break;
            case "F5": doType(VK_F5); break;
            case "F6": doType(VK_F6); break;
            case "F7": doType(VK_F7); break;
            case "F8": doType(VK_F8); break;
            case "F9": doType(VK_F9); break;
            case "F10": doType(VK_F10); break;
            case "F11": doType(VK_F11); break;
            case "F12": doType(VK_F12); break;

            default:
                throw new IllegalArgumentException("Funcao " + character + " nao encontrada!");
        }
    }

    void type(char character) {
        switch (character) {
            case 'a': doType(VK_A); break;
            case 'b': doType(VK_B); break;
            case 'c': doType(VK_C); break;
            case 'd': doType(VK_D); break;
            case 'e': doType(VK_E); break;
            case 'f': doType(VK_F); break;
            case 'g': doType(VK_G); break;
            case 'h': doType(VK_H); break;
            case 'i': doType(VK_I); break;
            case 'j': doType(VK_J); break;
            case 'k': doType(VK_K); break;
            case 'l': doType(VK_L); break;
            case 'm': doType(VK_M); break;
            case 'n': doType(VK_N); break;
            case 'o': doType(VK_O); break;
            case 'p': doType(VK_P); break;
            case 'q': doType(VK_Q); break;
            case 'r': doType(VK_R); break;
            case 's': doType(VK_S); break;
            case 't': doType(VK_T); break;
            case 'u': doType(VK_U); break;
            case 'v': doType(VK_V); break;
            case 'w': doType(VK_W); break;
            case 'x': doType(VK_X); break;
            case 'y': doType(VK_Y); break;
            case 'z': doType(VK_Z); break;
            case 'A': doType(VK_SHIFT, VK_A); break;
            case 'B': doType(VK_SHIFT, VK_B); break;
            case 'C': doType(VK_SHIFT, VK_C); break;
            case 'D': doType(VK_SHIFT, VK_D); break;
            case 'E': doType(VK_SHIFT, VK_E); break;
            case 'F': doType(VK_SHIFT, VK_F); break;
            case 'G': doType(VK_SHIFT, VK_G); break;
            case 'H': doType(VK_SHIFT, VK_H); break;
            case 'I': doType(VK_SHIFT, VK_I); break;
            case 'J': doType(VK_SHIFT, VK_J); break;
            case 'K': doType(VK_SHIFT, VK_K); break;
            case 'L': doType(VK_SHIFT, VK_L); break;
            case 'M': doType(VK_SHIFT, VK_M); break;
            case 'N': doType(VK_SHIFT, VK_N); break;
            case 'O': doType(VK_SHIFT, VK_O); break;
            case 'P': doType(VK_SHIFT, VK_P); break;
            case 'Q': doType(VK_SHIFT, VK_Q); break;
            case 'R': doType(VK_SHIFT, VK_R); break;
            case 'S': doType(VK_SHIFT, VK_S); break;
            case 'T': doType(VK_SHIFT, VK_T); break;
            case 'U': doType(VK_SHIFT, VK_U); break;
            case 'V': doType(VK_SHIFT, VK_V); break;
            case 'W': doType(VK_SHIFT, VK_W); break;
            case 'X': doType(VK_SHIFT, VK_X); break;
            case 'Y': doType(VK_SHIFT, VK_Y); break;
            case 'Z': doType(VK_SHIFT, VK_Z); break;
            case '`': doType(VK_BACK_QUOTE); break;
            case '0': doType(VK_NUMPAD0); break;
            case '1': doType(VK_NUMPAD1); break;
            case '2': doType(VK_NUMPAD2); break;
            case '3': doType(VK_NUMPAD3); break;
            case '4': doType(VK_NUMPAD4); break;
            case '5': doType(VK_NUMPAD5); break;
            case '6': doType(VK_NUMPAD6); break;
            case '7': doType(VK_NUMPAD7); break;
            case '8': doType(VK_NUMPAD8); break;
            case '9': doType(VK_NUMPAD9); break;
            case '-': doType(VK_MINUS); break;
            case '=': doType(VK_EQUALS); break;
            case '~': doType(VK_SHIFT, VK_BACK_QUOTE); break;
            case '!': doType(VK_EXCLAMATION_MARK); break;
            case '@': doType(VK_AT); break;
            case '#': doType(VK_NUMBER_SIGN); break;
            case '$': doType(VK_DOLLAR); break;
            case '%': doType(VK_SHIFT, VK_5); break;
            case '^': doType(VK_CIRCUMFLEX); break;
            case '&': doType(VK_AMPERSAND); break;
            case '*': doType(VK_ASTERISK); break;
            case '(': doType(VK_LEFT_PARENTHESIS); break;
            case ')': doType(VK_RIGHT_PARENTHESIS); break;
            case '_': doType(VK_UNDERSCORE); break;
            case '+': doType(VK_PLUS); break;
            case '\t': doType(VK_TAB); break;
            case '\n': doType(VK_ENTER); break;
            case '[': doType(VK_OPEN_BRACKET); break;
            case ']': doType(VK_CLOSE_BRACKET); break;
            case '\\': doType(VK_BACK_SLASH); break;
            case '{': doType(VK_SHIFT, VK_OPEN_BRACKET); break;
            case '}': doType(VK_SHIFT, VK_CLOSE_BRACKET); break;
            case '|': doType(VK_SHIFT, VK_BACK_SLASH); break;
            case ';': doType(VK_SEMICOLON); break;
            case ':': doType(VK_COLON); break;
            case '\'': doType(VK_QUOTE); break;
            case '"': doType(VK_QUOTEDBL); break;
            case ',': doType(VK_COMMA); break;
            case '<': doType(VK_SHIFT, VK_COMMA); break;
            case '.': doType(VK_PERIOD); break;
            case '>': doType(VK_SHIFT, VK_PERIOD); break;
            case '/': doType(VK_SLASH); break;
            case '?': doType(VK_SHIFT, VK_SLASH); break;
            case ' ': doType(VK_SPACE); break;

            default:
                throw new IllegalArgumentException("Cannot type character " + character);
        }
    }

    void doType(int... keyCodes) {
        doType(keyCodes, 0, keyCodes.length);
    }

    private void doType(int[] keyCodes, int offset, int length) {
        if (length == 0) {
            return;
        }

        robot.keyPress(keyCodes[offset]);
        doType(keyCodes, offset + 1, length - 1);
        robot.keyRelease(keyCodes[offset]);
    }

    private final WebElement getElement(String componentId) {
        return getWiniumDriver().findElement(By.id(componentId));
    }

    private final WebElement getElement(WebElement parent, String elementId) {
        return parent.findElement(By.id(elementId));
    }

    private final String getElementValue(WebElement element) {
        if (element.getAttribute("ControlType").equalsIgnoreCase("ControlType.Text") ||
                element.getAttribute("ControlType").equalsIgnoreCase("ControlType.Button") ||
                element.getAttribute("ControlType").equalsIgnoreCase("ControlType.HeaderItem")) {
            return element.getAttribute("Name");
        }

        return element.getText();
    }

    public final String getElementValue(String parent, String elementId) {
        if (parent != null) {
            return getElementValue(getElement(getElement(parent), elementId));
        }
        return getElementValue(getElementName(elementId));
    }

    public final String getElementValue(String elementId) {
        return getElementValue(getElement(elementId));
    }

    public final void setElementText(String componentId, String text) {
        getElement(componentId).sendKeys(text);
    }

    public final void setElementValueNumbersByKey(String componentId, String valueKeys) throws Exception {
        clickElement(componentId);
        clearField();

        if (valueKeys == null || valueKeys.trim().isEmpty()) {
            return;
        }
        valueKeys = valueKeys.replaceAll("/", "").replaceAll("\\.", "");
        digitar(valueKeys);
    }

    public final String getElementName(String elementId) {
        WebElement component = getElement(elementId);
        return component.getAttribute("Name");
    }

    public final void clickElement(String componentId, Boolean safeNoSuch) {
        try {
            WebElement element = getElement(componentId);
            element.click();
        } catch (Exception e) {
            if (!safeNoSuch) {
                logPrintFail("Elemento nao encontrado: " + componentId);
                Assert.fail("Elemento nao encontrado: " + componentId);
            }
        }
    }

    public final void clickElement(String componentId) {
        clickElement(componentId, Boolean.FALSE);
    }

    public Boolean existsComponent(String componentId) {
        return !getWiniumDriver().findElements(By.id(componentId)).isEmpty();
    }

    public Boolean existsComponent(String parentId, String componentId) {
        return !getElement(parentId).findElements(By.id(componentId)).isEmpty();
    }

    public String[] getComboValues(String id) {
        ComboBox comboBox = new ComboBox(getElement(id));
        ListBox listBox = new ListBox(getElement(comboBox, id));
        List<WebElement> items = listBox.findElements(By.id(""));

        return items.stream().map(e -> e.getAttribute("Name")).toArray(String[]::new);
    }

    public String getComboSelectedValue(String id) {
        ComboBox comboBox = new ComboBox(getElement(id));
        return comboBox.findSelected().getAttribute("Name");
    }

    public final void setComboSelectedValue(String id, String valueKeys) {
        clickElement(id);

        if (valueKeys == null || valueKeys.trim().isEmpty()) {
            digitar(KeyEvent.VK_HOME);
            digitar(KeyEvent.VK_ENTER);
            return;
        }

        String[] values = getComboValues(id);

        digitar(KeyEvent.VK_HOME);

        for (int i = 0; i < values.length; i++) {
            if (values[i].equalsIgnoreCase(valueKeys)) {
                if (i > 0) {
                    pressKey(KeyEvent.VK_DOWN, i);
                    break;
                }
            }
        }

        digitar(KeyEvent.VK_ENTER);
    }

    public String[] getTableHeaders(String id) {
        DataGrid dataGrid = new DataGrid(getElement(id));
        List<WebElement> items = dataGrid.findElements(By.id(""));

        String[] headers = new String[dataGrid.getColumnCount()];

        for (int i = 0; i < dataGrid.getColumnCount(); i++) {
            headers[i] = getElementValue(id, "HeaderItem " + i);
        }
        return headers;
    }

    public void setSelectTableValue(String id, int row) {
        DataGrid dataGrid = new DataGrid(getElement(id));
        dataGrid.select(row, 0);

    }

    private <T> T convert(Class<T> clazz, String value) {
        switch (clazz.getSimpleName()) {
            case "Integer":
                return value.trim().isEmpty() ? null : (T) Integer.valueOf(value);
            case "Long":
                return value.trim().isEmpty() ? null : (T) Long.valueOf(value);
            case "LocalDate":
                return value.trim().isEmpty() ? null : (T) LocalDate.parse(value, DateTimeFormatter.ofPattern("dd/MM/yyy"));
            case "BigDecimal":
                return value.trim().isEmpty() ? null : (T) new BigDecimal(value.replaceAll("\\.", "")
                        .replaceAll(",", "."));
            default:
                return (T) value;
        }
    }

    public List<Map<String, String>> getTableValues(String id) {
        DataGrid dataGrid = new DataGrid(getElement(id));
        List<WebElement> items = dataGrid.findElements(By.id(""));

        Map<String, String> line = new LinkedHashMap<>();
        List<Map<String, String>> lines = new ArrayList<>(dataGrid.getRowCount());

        Integer index = 0;

        String[] headers = getTableHeaders(id);

        for (WebElement e : items) {
            if (e.getAttribute("ControlType").equalsIgnoreCase("ControlType.Text")) {
                line.put(headers[index], e.getAttribute("Name"));
                index++;

                if (index == dataGrid.getColumnCount()) {
                    lines.add(line);
                    index = 0;
                    line = new LinkedHashMap<>();
                }
            }
        }
        getWiniumDriver().findElement(By.id(id)).click();
        return lines;
    }

    public <T> List<T> getTableValues(String id, Class<T> clazz) throws Exception {
        DataGrid dataGrid = new DataGrid(getElement(id));
        List<WebElement> items = dataGrid.findElements(By.id(""));

        int index = 0;
        String[] headers = getTableHeaders(id);
        List<T> retornos = new ArrayList<>();
        Map<String, Method> methods = new HashMap<>();

        for (Field field : clazz.getDeclaredFields()) {
            if (field.isAnnotationPresent(ColumnName.class)) {
                ColumnName columnName = field.getAnnotation(ColumnName.class);
                Method method = clazz.getMethod("set" + StringUtils.capitalize(field.getName()), field.getType());
                methods.put(columnName.value(), method);
            }
        }

        T retorno = clazz.getConstructor().newInstance();
        for (WebElement e : items) {
            if (e.getAttribute("ControlType").equalsIgnoreCase("ControlType.Text")) {
                Method method = methods.get(headers[index]);
                method.invoke(retorno, convert(method.getParameterTypes()[0], e.getAttribute("Name")));

                index++;

                if (index == dataGrid.getColumnCount()) {
                    retornos.add(retorno);
                    index = 0;
                    retorno = clazz.getConstructor().newInstance();
                }
            }
        }
        return retornos;
    }

    private void clearField() throws Exception {
        Robot robot = new Robot();
        robot.keyPress(KeyEvent.VK_CONTROL);
        robot.keyPress(KeyEvent.VK_DELETE);
        robot.keyRelease(KeyEvent.VK_DELETE);
        robot.keyRelease(KeyEvent.VK_CONTROL);
    }

    public static final Integer[] NUMLOCK = new Integer[]{
            KeyEvent.VK_NUMPAD0,
            KeyEvent.VK_NUMPAD1,
            KeyEvent.VK_NUMPAD2,
            KeyEvent.VK_NUMPAD3,
            KeyEvent.VK_NUMPAD4,
            KeyEvent.VK_NUMPAD5,
            KeyEvent.VK_NUMPAD6,
            KeyEvent.VK_NUMPAD7,
            KeyEvent.VK_NUMPAD8,
            KeyEvent.VK_NUMPAD9
    };

    public void limpaCampo(String campo) {
        try {
            clickElement(campo, true);
            clearField();
        } catch (Exception e) {
            logPrintFail("Nao conseguiu limpar o campo com id: " + campo + " - " + e.getMessage());
        }
    }
}
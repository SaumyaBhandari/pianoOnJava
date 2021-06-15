import javax.swing.*;
import javax.sound.midi.*;
import java.awt.*;
import java.awt.event.*;

public class Piano extends JComponent implements ActionListener, KeyListener {

    Synthesizer midiSynth;
    Instrument[] instr;
    MidiChannel[] mChannels;
    int height = 700;
    int width = 1600;
    Boolean isKeyPressed = false;
    int noteToBePlayed;
    int keyNo = 88;

    public Piano() {
        try {

            midiSynth = MidiSystem.getSynthesizer();
            midiSynth.open();
            instr = midiSynth.getDefaultSoundbank().getInstruments();
            mChannels = midiSynth.getChannels();
            midiSynth.loadInstrument(instr[0]);// load an instrument

        } catch (MidiUnavailableException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {

        Piano sort = new Piano();
        JFrame frame = new JFrame("Sorting Application");
        frame.setSize(sort.width, sort.height);
        frame.setLocation(100, 50);
        frame.add(sort);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.addKeyListener(sort);
        Timer t = new Timer(10, sort);
        t.start();

        frame.setVisible(true);
    }

    @Override
    protected void paintComponent(Graphics g) {

        int location = 0;
        for (int i = 0; i < 8; i++) {
            g.setColor(Color.decode("#ffffff"));
            if (i == keyNo) {
                g.setColor(Color.decode("#aaaaaa"));
            }
            g.fillRect(location, 0, width / 8, height);
            g.setColor(Color.decode("#212121"));
            g.drawRect(location, 0, width / 8, height);
            location += width / 8;
        }

        location = width / 8 - (width / 8) / 4;

        for (int i = 8; i < 14; i++) {
            if (i == 10) {
                location += width / 8;
                continue;
            }
            g.setColor(Color.decode("#444444"));

            if (i == keyNo) {
                g.setColor(Color.decode("#ff00ff"));
                g.drawRect(location, 0, width / 16, height / 2);
                g.setColor(Color.decode("#000000"));
            }
            g.fillRect(location, 0, width / 16, height / 2);

            location += width / 8;
        }

    }

    public void playNote(int num) {
        mChannels[0].noteOn(num, 100);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        repaint();
    }

    @Override
    public void keyTyped(KeyEvent e) {

    }

    @Override
    public void keyPressed(KeyEvent e) {
        isKeyPressed = true;
        if (e.getKeyCode() == KeyEvent.VK_A) {
            playNote(60);
            keyNo = 0;
        }
        if (e.getKeyCode() == KeyEvent.VK_W) {
            playNote(61);
            keyNo = 8;
        }
        if (e.getKeyCode() == KeyEvent.VK_S) {
            playNote(62);
            keyNo = 1;
        }
        if (e.getKeyCode() == KeyEvent.VK_E) {
            playNote(63);
            keyNo = 9;
        }
        if (e.getKeyCode() == KeyEvent.VK_D) {
            playNote(64);
            keyNo = 2;
        }
        if (e.getKeyCode() == KeyEvent.VK_F) {
            playNote(65);
            keyNo = 3;
        }
        if (e.getKeyCode() == KeyEvent.VK_T) {
            playNote(66);
            keyNo = 11;
        }
        if (e.getKeyCode() == KeyEvent.VK_G) {
            playNote(67);
            keyNo = 4;
        }
        if (e.getKeyCode() == KeyEvent.VK_Y) {
            playNote(68);
            keyNo = 12;
        }
        if (e.getKeyCode() == KeyEvent.VK_H) {
            playNote(69);
            keyNo = 5;
        }
        if (e.getKeyCode() == KeyEvent.VK_U) {
            playNote(70);
            keyNo = 13;
        }
        if (e.getKeyCode() == KeyEvent.VK_J) {
            playNote(71);
            keyNo = 6;
        }
        if (e.getKeyCode() == KeyEvent.VK_K) {
            playNote(72);
            keyNo = 7;
        }

    }

    @Override
    public void keyReleased(KeyEvent e) {
        isKeyPressed = false;
        keyNo = 500;
    }

}

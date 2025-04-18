/*
 * Licensed to the Apache Software Foundation (ASF) under one
 * or more contributor license agreements.  See the NOTICE file
 * distributed with this work for additional information
 * regarding copyright ownership.  The ASF licenses this file
 * to you under the Apache License, Version 2.0 (the
 * "License"); you may not use this file except in compliance
 * with the License.  You may obtain a copy of the License at
 *
 *   http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing,
 * software distributed under the License is distributed on an
 * "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY
 * KIND, either express or implied.  See the License for the
 * specific language governing permissions and limitations
 * under the License.
 */

package org.netbeans.modules.options.colors;

import java.awt.Color;
import java.awt.Component;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import javax.swing.AbstractButton;
import javax.swing.JComponent;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.ListSelectionModel;
import javax.swing.text.AttributeSet;
import javax.swing.text.SimpleAttributeSet;
import javax.swing.text.StyleConstants;
import org.netbeans.api.editor.settings.EditorStyleConstants;
import org.netbeans.api.options.OptionsDisplayer;
import org.netbeans.modules.options.colors.spi.FontsColorsController;
import org.netbeans.spi.options.OptionsPanelController;
import org.openide.awt.ColorComboBox;
import org.openide.awt.Mnemonics;
import org.openide.util.NbBundle;

/**
 *
 * @author  Jan Jancura
 */
@OptionsPanelController.Keywords(keywords = {"#KW_AnnotationPanel"}, location = OptionsDisplayer.FONTSANDCOLORS, tabTitle="#Annotations_tab.displayName")
public class AnnotationsPanel extends JPanel implements ActionListener, 
    ItemListener, FontsColorsController {
    
    private ColorModel colorModel;
    /// Currently displayed profile.
    private String currentProfile;
    /// History of all profiles and their attributes which were at some point displayed in this panel. May hold modified data.
    /// null value indicates the profile is marked for deletion.
    private Map<String, List<AttributeSet>> profiles;
    /// Profile names to save.
    private Set<String> toBeSaved;

    private boolean changed;
    private boolean listen;
    
    
    /** Creates new form AnnotationsPanel1 */
    public AnnotationsPanel() {
        initComponents();

        setName(loc("Annotations_tab")); //NOI18N
        
        // 1) init components
        cbForeground.getAccessibleContext ().setAccessibleName (loc ("AN_Foreground_Chooser"));
        cbForeground.getAccessibleContext ().setAccessibleDescription (loc ("AD_Foreground_Chooser"));
        cbBackground.getAccessibleContext ().setAccessibleName (loc ("AN_Background_Chooser"));
        cbBackground.getAccessibleContext ().setAccessibleDescription (loc ("AD_Background_Chooser"));
        cbEffectColor.getAccessibleContext ().setAccessibleName (loc ("AN_Wave_Underlined"));
        cbEffectColor.getAccessibleContext ().setAccessibleDescription (loc ("AD_Wave_Underlined"));
        lCategories.getAccessibleContext ().setAccessibleName (loc ("AN_Categories"));
        lCategories.getAccessibleContext ().setAccessibleDescription (loc ("AD_Categories"));
        lCategories.setSelectionMode (ListSelectionModel.SINGLE_SELECTION);
        lCategories.setVisibleRowCount (3);
        lCategories.addListSelectionListener(evt -> {
            if (listen) {
                refreshUI();
            }
        });
	lCategories.setCellRenderer (new CategoryRenderer ());
        cbForeground.addItemListener(this);
        cbBackground.addItemListener(this);
        cbEffectColor.addItemListener(this);
        
        lCategory.setLabelFor (lCategories);
        loc(lCategory, "CTL_Category");
        loc(lForeground, "CTL_Foreground_label");
        loc(lEffectColor, "CTL_Effects_color");
        loc(lbackground, "CTL_Background_label");
        
        cbEffects.addItem (loc ("CTL_Effects_None"));
        cbEffects.addItem (loc ("CTL_Effects_Wave_Underlined"));
        cbEffects.getAccessibleContext ().setAccessibleName (loc ("AN_Effects"));
        cbEffects.getAccessibleContext ().setAccessibleDescription (loc ("AD_Effects"));
        cbEffects.addActionListener (this);
        clearState();
    }
    
    /** This method is called from within the constructor to
     * initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is
     * always regenerated by the Form Editor.
     */
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        lCategory = new javax.swing.JLabel();
        cpCategories = new javax.swing.JScrollPane();
        lCategories = new javax.swing.JList<AttributeSet>();
        lForeground = new javax.swing.JLabel();
        lbackground = new javax.swing.JLabel();
        lEffectColor = new javax.swing.JLabel();
        cbForeground = new ColorComboBox();
        cbBackground = new ColorComboBox();
        cbEffectColor = new ColorComboBox();
        lEffects = new javax.swing.JLabel();
        cbEffects = new javax.swing.JComboBox();

        lCategory.setText(org.openide.util.NbBundle.getMessage(AnnotationsPanel.class, "CTL_Category")); // NOI18N

        cpCategories.setViewportView(lCategories);

        lForeground.setText(org.openide.util.NbBundle.getMessage(AnnotationsPanel.class, "CTL_Foreground_label")); // NOI18N

        lbackground.setText(org.openide.util.NbBundle.getMessage(AnnotationsPanel.class, "CTL_Background_label")); // NOI18N

        lEffectColor.setText(org.openide.util.NbBundle.getMessage(AnnotationsPanel.class, "CTL_Effects_color")); // NOI18N

        lEffects.setLabelFor(cbEffects);
        lEffects.setText(org.openide.util.NbBundle.getMessage(AnnotationsPanel.class, "CTL_Effects_label")); // NOI18N

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addComponent(cpCategories, javax.swing.GroupLayout.DEFAULT_SIZE, 168, Short.MAX_VALUE)
                        .addGap(20, 20, 20)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(lbackground)
                            .addComponent(lForeground)
                            .addComponent(lEffects)
                            .addComponent(lEffectColor))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(cbEffectColor, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(cbForeground, javax.swing.GroupLayout.Alignment.TRAILING, 0, 138, Short.MAX_VALUE)
                            .addComponent(cbBackground, javax.swing.GroupLayout.Alignment.TRAILING, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(cbEffects, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                    .addComponent(lCategory))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(lCategory)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(lForeground)
                            .addComponent(cbForeground, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(lbackground)
                            .addComponent(cbBackground, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(7, 7, 7)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(lEffects)
                            .addComponent(cbEffects, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(10, 10, 10)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(lEffectColor)
                            .addComponent(cbEffectColor, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addComponent(cpCategories, javax.swing.GroupLayout.DEFAULT_SIZE, 258, Short.MAX_VALUE))
                .addContainerGap())
        );

        lEffects.getAccessibleContext().setAccessibleName("&Effect:");
    }// </editor-fold>//GEN-END:initComponents
    
    
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JComboBox cbBackground;
    private javax.swing.JComboBox cbEffectColor;
    private javax.swing.JComboBox cbEffects;
    private javax.swing.JComboBox cbForeground;
    private javax.swing.JScrollPane cpCategories;
    private javax.swing.JList<AttributeSet> lCategories;
    private javax.swing.JLabel lCategory;
    private javax.swing.JLabel lEffectColor;
    private javax.swing.JLabel lEffects;
    private javax.swing.JLabel lForeground;
    private javax.swing.JLabel lbackground;
    // End of variables declaration//GEN-END:variables
    
 
    @Override
    public void actionPerformed (ActionEvent evt) {
        if (!listen) return;
        if (evt.getSource () == cbEffects) {
            if (cbEffects.getSelectedIndex () == 0) {
                cbEffectColor.setSelectedItem(null);
            } else if (cbEffectColor.getSelectedItem() == null) {
                cbEffectColor.setSelectedIndex(0);
            }
	    cbEffectColor.setEnabled (cbEffects.getSelectedIndex () > 0);
            updateData ();
	}
        updateData ();
        fireChanged();
    }
    
    @Override
    public void itemStateChanged( ItemEvent e ) {
        if( e.getStateChange() == ItemEvent.DESELECTED )
            return;
        if (!listen) return;
        updateData ();
        fireChanged();
    }

    private void clearState() {
        toBeSaved = new HashSet<>();
        profiles = new HashMap<>();
        changed = false;
        currentProfile = null;
    }
    
    @Override
    public void update(ColorModel colorModel) {
        clearState();
        this.colorModel = colorModel;
        setCurrentProfile(colorModel.getCurrentProfile());
        toBeSaved.remove(currentProfile);
        changed = false;
    }
    
    @Override
    public void cancel() {
        clearState();
    }
    
    @Override
    public void applyChanges() {
        if (colorModel == null) return;
        boolean currentChanged = toBeSaved.remove(currentProfile);
        for (String scheme : toBeSaved) {
            colorModel.setAnnotations(scheme, getAnnotations(scheme));
        }
        // TODO the editor sometimes refreshes to whatever is set last instead of the current profile
        // Setting current profile last fixes it in most cases. Restart helps too.
        if (currentChanged) {
            colorModel.setAnnotations(currentProfile, getAnnotations(currentProfile));
        }
        clearState();
    }
    
    @Override
    public boolean isChanged () {
        return changed;
    }
    
    @Override
    public void setCurrentProfile(String profile) {
        if (profile.equals(currentProfile)) {
            return;
        }
        // copy current if profile is new
        if (!profiles.containsKey(profile)) {
            if (!colorModel.getProfiles().contains(profile)) {
                profiles.put(profile, copy(getAnnotations(currentProfile)));
            }
        }
        toBeSaved.add(profile);
        currentProfile = profile;
        invokeWithoutListeners(() -> {
            int selected = Math.max(lCategories.getSelectedIndex(), 0);
            lCategories.setListData(getAnnotations(profile).toArray(AttributeSet[]::new));
            if (lCategories.getModel().getSize() > selected) {
                lCategories.setSelectedIndex(selected);
            }
        });
        refreshUI();
        fireChanged();
    }
    
    @Override
    public void deleteProfile(String profile) {
        if (colorModel.isCustomProfile(profile)) {
            // mark for deletion
            profiles.put(profile, null);
        } else {
            // restore default profile
            profiles.put(profile, copy(getDefaults(profile)));
            invokeWithoutListeners(() -> {
                int selected = Math.max(lCategories.getSelectedIndex(), 0);
                lCategories.setListData(getAnnotations(profile).toArray(AttributeSet[]::new));
                if (lCategories.getModel().getSize() > selected) {
                    lCategories.setSelectedIndex(selected);
                }
            });
            refreshUI();
        }
        toBeSaved.add(profile); // restore or delete
        fireChanged();
    }

    @Override
    public JComponent getComponent() {
        return this;
    }
        
    // other methods ...........................................................
    
    private static String loc (String key) {
        return NbBundle.getMessage (SyntaxColoringPanel.class, key);
    }
    
    private static void loc(Component c, String key) {
        if (c instanceof AbstractButton button) {
            Mnemonics.setLocalizedText(button, loc(key));
        } else {
            Mnemonics.setLocalizedText((JLabel) c,loc(key));
        }
    }
    
    private void invokeWithoutListeners(Runnable run) {
        try {
            listen = false;
            run.run();
        } finally {
            listen = true;
        }
    }

    private void updateData () {
        List<AttributeSet> annotations = getAnnotations(currentProfile);
        int index = lCategories.getSelectedIndex();
        SimpleAttributeSet c = new SimpleAttributeSet(annotations.get(index));
        
        Color color = ColorComboBoxSupport.getSelectedColor( (ColorComboBox)cbBackground );
        if (color != null) {
            c.addAttribute(StyleConstants.Background, color);
        } else {
            c.removeAttribute(StyleConstants.Background);
        }
        
        color = ColorComboBoxSupport.getSelectedColor( (ColorComboBox)cbForeground );
        if (color != null) {
            c.addAttribute(StyleConstants.Foreground, color);
        } else {
            c.removeAttribute(StyleConstants.Foreground);
        }
        
        Color wave = null;
        if (cbEffects.getSelectedIndex () == 1)
             wave = ColorComboBoxSupport.getSelectedColor( (ColorComboBox)cbEffectColor );
        if (wave != null) {
            c.addAttribute(EditorStyleConstants.WaveUnderlineColor, wave);
        } else {
            c.removeAttribute(EditorStyleConstants.WaveUnderlineColor);
        }
        
        annotations.set(index, c);
        toBeSaved.add(currentProfile);
    }
    
    private void fireChanged() {
        boolean isChanged = false;
        for (String profile : toBeSaved) {
            List<AttributeSet> attributeSet = getAnnotations(profile);
            Map<String, AttributeSet> savedAnnotations = toMap(colorModel.getAnnotations(profile));
            Map<String, AttributeSet> currentAnnotations = toMap(attributeSet);
            if (savedAnnotations != null && currentAnnotations != null) {
                if (savedAnnotations.size() >= currentAnnotations.size()) {
                    isChanged |= checkMaps(savedAnnotations, currentAnnotations);
                } else {
                    isChanged |= checkMaps(currentAnnotations, savedAnnotations);
                }
            } else if (savedAnnotations != null && currentAnnotations == null) {
                isChanged = true;
            }
            if (isChanged) { // no need to iterate further
                changed = true;
                return;
            }
        }
        changed = isChanged;
    }
    
    private boolean checkMaps(Map<String, AttributeSet> savedMap, Map<String, AttributeSet> currentMap) {
        boolean isChanged = false;
        for (String name : savedMap.keySet()) {
            if (currentMap.containsKey(name)) {
                AttributeSet currentAS = currentMap.get(name);
                AttributeSet savedAS = savedMap.get(name);
                Color currentForeground = (Color) currentAS.getAttribute(StyleConstants.Foreground);
                Color savedForeground = (Color) savedAS.getAttribute(StyleConstants.Foreground);
                Color currentBackground = (Color) currentAS.getAttribute(StyleConstants.Background);
                Color savedBackground = (Color) savedAS.getAttribute(StyleConstants.Background);
                Color currentWave = (Color) currentAS.getAttribute(EditorStyleConstants.WaveUnderlineColor);
                Color savedWave = (Color) savedAS.getAttribute(EditorStyleConstants.WaveUnderlineColor);
                isChanged |= (currentForeground == null ? savedForeground != null : !currentForeground.equals(savedForeground))
                        || (currentBackground == null ? savedBackground != null : !currentBackground.equals(savedBackground))
                        || (currentWave == null ? savedWave != null : !currentWave.equals(savedWave));
                if(isChanged) { // no need to iterate further
                    return true;
                }
            }
        }
        return isChanged;
    }
    
    private Map<String, AttributeSet> toMap(Collection<AttributeSet> categories) {
        if (categories == null) return null;
        Map<String, AttributeSet> result = new HashMap<>();
        for(AttributeSet as : categories) {
            result.put((String) as.getAttribute(StyleConstants.NameAttribute), as);
        }
        return result;
    }
    
    private void refreshUI () {
        int index = lCategories.getSelectedIndex ();
        if (index < 0) {
	    // no category selected
            cbForeground.setEnabled (false);
            cbBackground.setEnabled (false);
            cbEffectColor.setEnabled (false);
            return;
        }
        cbForeground.setEnabled (true);
        cbBackground.setEnabled (true);
        cbEffectColor.setEnabled (true);
        
        invokeWithoutListeners(() -> {
            // set defaults
            AttributeSet defAs = getDefaultColoring(currentProfile);
            if (defAs != null) {
                Color inheritedForeground = (Color) defAs.getAttribute(StyleConstants.Foreground);
                if (inheritedForeground == null) {
                    inheritedForeground = Color.black;
                }
                ColorComboBoxSupport.setInheritedColor((ColorComboBox)cbForeground, inheritedForeground);

                Color inheritedBackground = (Color) defAs.getAttribute(StyleConstants.Background);
                if (inheritedBackground == null) {
                    inheritedBackground = Color.white;
                }
                ColorComboBoxSupport.setInheritedColor((ColorComboBox)cbBackground, inheritedBackground);
            }

            // set values
            AttributeSet c = getAnnotations(currentProfile).get(index);
            ColorComboBoxSupport.setSelectedColor((ColorComboBox)cbForeground, (Color)c.getAttribute(StyleConstants.Foreground));
            ColorComboBoxSupport.setSelectedColor((ColorComboBox)cbBackground, (Color)c.getAttribute(StyleConstants.Background));

            if (c.getAttribute(EditorStyleConstants.WaveUnderlineColor) != null) {
                cbEffects.setSelectedIndex(1);
                cbEffectColor.setEnabled(true);
                ((ColorComboBox)cbEffectColor).setSelectedColor((Color) c.getAttribute (EditorStyleConstants.WaveUnderlineColor));
            } else {
                cbEffects.setSelectedIndex(0);
                cbEffectColor.setEnabled(false);
                cbEffectColor.setSelectedIndex(-1);
            }
        });
    }
    
    private AttributeSet getDefaultColoring(String profile) {
        for (AttributeSet set : colorModel.getCategories(profile, ColorModel.ALL_LANGUAGES)) {
            if ("default".equals(set.getAttribute(StyleConstants.NameAttribute))) { //NOI18N
                return set.copyAttributes();
            }
        }
        return null;
    }
    
    private List<AttributeSet> getAnnotations(String profile) {
        if (!profiles.containsKey(profile)) {
            List<AttributeSet> copy = copy(colorModel.getAnnotations(profile));
            copy.sort(new CategoryComparator());
            profiles.put(profile, copy);
        }
        return profiles.get(profile);
    }
    
    private List<AttributeSet> getDefaults(String profile) {
        List<AttributeSet> defaults = new ArrayList<>(colorModel.getAnnotationsDefaults(profile));
        defaults.sort(new CategoryComparator());
        return defaults;
    }

    private static List<AttributeSet> copy(Collection<AttributeSet> annotations) {
        List<AttributeSet> copy = new ArrayList<>(annotations.size());
        for (AttributeSet set : annotations) {
            copy.add(set.copyAttributes());
        }
        return copy;
    }

}

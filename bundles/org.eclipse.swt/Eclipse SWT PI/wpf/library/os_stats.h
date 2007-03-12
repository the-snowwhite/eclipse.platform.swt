/*******************************************************************************
 * Copyright (c) 2000, 2007 IBM Corporation and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *    IBM Corporation - initial API and implementation
 *******************************************************************************/

#ifdef NATIVE_STATS
extern int OS_nativeFunctionCount;
extern int OS_nativeFunctionCallCount[];
extern char* OS_nativeFunctionNames[];
#define OS_NATIVE_ENTER(env, that, func) OS_nativeFunctionCallCount[func]++;
#define OS_NATIVE_EXIT(env, that, func) 
#else
#ifndef OS_NATIVE_ENTER
#define OS_NATIVE_ENTER(env, that, func) 
#endif
#ifndef OS_NATIVE_EXIT
#define OS_NATIVE_EXIT(env, that, func) 
#endif
#endif

typedef enum {
	AccessText_1AccessKey_FUNC,
	AccessText_1Text_FUNC,
	ApplicationCommands_1Cut_FUNC,
	ApplicationCommands_1Paste_FUNC,
	ApplicationCommands_1Redo_FUNC,
	ApplicationCommands_1Undo_FUNC,
	Application_1Dispatcher_FUNC,
	Application_1Run_FUNC,
	Application_1Shutdown_FUNC,
	Application_1ShutdownMode_FUNC,
	Application_1Windows_FUNC,
	ArrayList_1Clear_FUNC,
	ArrayList_1Count_FUNC,
	ArrayList_1Insert_FUNC,
	ArrayList_1RemoveAt_FUNC,
	ArrayList_1default__II_FUNC,
	ArrayList_1default__III_FUNC,
	BindingExpression_1UpdateTarget_FUNC,
	Binding_1Converter_FUNC,
	Binding_1ConverterParameter_FUNC,
	BitmapDecoder_1Create_FUNC,
	BitmapDecoder_1Frames_FUNC,
	BitmapEncoder_1Frames_FUNC,
	BitmapEncoder_1Save_FUNC,
	BitmapFrameCollection_1Add_FUNC,
	BitmapFrameCollection_1default_FUNC,
	BitmapFrame_1Create_FUNC,
	BitmapImage_1BeginInit_FUNC,
	BitmapImage_1CreateOptions_FUNC,
	BitmapImage_1EndInit_FUNC,
	BitmapImage_1UriSource_FUNC,
	BitmapPalette_1Colors_FUNC,
	BitmapSource_1Clone_FUNC,
	BitmapSource_1CopyPixels_FUNC,
	BitmapSource_1Create_FUNC,
	BitmapSource_1Format_FUNC,
	BitmapSource_1Palette_FUNC,
	BitmapSource_1PixelHeight_FUNC,
	BitmapSource_1PixelWidth_FUNC,
	Bitmap_1GetHicon_FUNC,
	Border_1typeid_FUNC,
	Brush_1Opacity_FUNC,
	Brushes_1Black_FUNC,
	Brushes_1LightSkyBlue_FUNC,
	Brushes_1Navy_FUNC,
	Brushes_1Transparent_FUNC,
	Brushes_1White_FUNC,
	ButtonBase_1Click_FUNC,
	Button_1IsDefault__I_FUNC,
	Button_1IsDefault__IZ_FUNC,
	CancelEventArgs_1Cancel_FUNC,
	Canvas_1GetLeft_FUNC,
	Canvas_1GetTop_FUNC,
	Canvas_1SetLeft_FUNC,
	Canvas_1SetTop_FUNC,
	CharacterHit_1FirstCharacterIndex_FUNC,
	CharacterHit_1TrailingLength_FUNC,
	CheckBox_1typeid_FUNC,
	Clipboard_1ContainsData_FUNC,
	Clipboard_1GetData_FUNC,
	Clipboard_1GetText_FUNC,
	Clipboard_1SetData_FUNC,
	CloseHandle_FUNC,
	ColorDialog_1AnyColor_FUNC,
	ColorDialog_1Color__I_FUNC,
	ColorDialog_1Color__II_FUNC,
	ColorDialog_1CustomColors__I_FUNC,
	ColorDialog_1CustomColors__II_FUNC,
	ColorList_1Add_FUNC,
	ColorList_1Count_FUNC,
	ColorList_1Current_FUNC,
	ColorList_1GetEnumerator_FUNC,
	Color_1A_FUNC,
	Color_1B_FUNC,
	Color_1FromArgb_FUNC,
	Color_1G_FUNC,
	Color_1R_FUNC,
	Colors_1Black_FUNC,
	Colors_1Blue_FUNC,
	Colors_1Cyan_FUNC,
	Colors_1DarkGray_FUNC,
	Colors_1Green_FUNC,
	Colors_1LightSkyBlue_FUNC,
	Colors_1Lime_FUNC,
	Colors_1Magenta_FUNC,
	Colors_1Maroon_FUNC,
	Colors_1Navy_FUNC,
	Colors_1Olive_FUNC,
	Colors_1Purple_FUNC,
	Colors_1Red_FUNC,
	Colors_1Silver_FUNC,
	Colors_1Teal_FUNC,
	Colors_1Transparent_FUNC,
	Colors_1White_FUNC,
	Colors_1Yellow_FUNC,
	ColumnDefinitionCollection_1Add_FUNC,
	ColumnDefinition_1Width_FUNC,
	ComboBox_1IsDropDownOpen__I_FUNC,
	ComboBox_1IsDropDownOpen__IZ_FUNC,
	ComboBox_1IsEditable_FUNC,
	ComboBox_1SelectionBoxItem_FUNC,
	CommandManager_1AddPreviewExecutedHandler_FUNC,
	CommonDialog_1ShowDialog_FUNC,
	CompositeCollection_1IndexOf_FUNC,
	CompositeCollection_1Insert_FUNC,
	CompositeCollection_1Remove_FUNC,
	CompositeCollection_1RemoveAt_FUNC,
	Console_1Beep_FUNC,
	ContainerVisual_1Clip__I_FUNC,
	ContainerVisual_1Clip__II_FUNC,
	ContentControl_1Content__I_FUNC,
	ContentControl_1Content__II_FUNC,
	ContentPresenter_1Content_FUNC,
	ContentPresenter_1typeid_FUNC,
	ContextMenuEventArgs_1CursorLeft_FUNC,
	ContextMenuEventArgs_1CursorTop_FUNC,
	ContextMenu_1Closed_FUNC,
	ContextMenu_1HorizontalOffset_FUNC,
	ContextMenu_1IsOpen_FUNC,
	ContextMenu_1Opened_FUNC,
	ContextMenu_1Placement_FUNC,
	ContextMenu_1VerticalOffset_FUNC,
	Control_1Background_FUNC,
	Control_1BackgroundProperty_FUNC,
	Control_1BorderThickness_FUNC,
	Control_1FontFamily_FUNC,
	Control_1FontFamilyProperty_FUNC,
	Control_1FontSize_FUNC,
	Control_1FontSizeProperty_FUNC,
	Control_1FontStretch_FUNC,
	Control_1FontStretchProperty_FUNC,
	Control_1FontStyle_FUNC,
	Control_1FontStyleProperty_FUNC,
	Control_1FontWeight_FUNC,
	Control_1FontWeightProperty_FUNC,
	Control_1Foreground_FUNC,
	Control_1ForegroundProperty_FUNC,
	Control_1HorizontalContentAlignment__I_FUNC,
	Control_1HorizontalContentAlignment__II_FUNC,
	Control_1MouseDoubleClick_FUNC,
	Control_1Template__I_FUNC,
	Control_1Template__II_FUNC,
	Control_1VerticalContentAlignment_FUNC,
	CreateCursor_FUNC,
	CreateIconIndirect_FUNC,
	CreateProcessW_FUNC,
	CultureInfo_1CurrentUICulture_FUNC,
	CursorInteropHelper_1Create_FUNC,
	Cursors_1AppStarting_FUNC,
	Cursors_1Arrow_FUNC,
	Cursors_1Cross_FUNC,
	Cursors_1Hand_FUNC,
	Cursors_1Help_FUNC,
	Cursors_1IBeam_FUNC,
	Cursors_1No_FUNC,
	Cursors_1ScrollE_FUNC,
	Cursors_1ScrollN_FUNC,
	Cursors_1ScrollNE_FUNC,
	Cursors_1ScrollNW_FUNC,
	Cursors_1ScrollS_FUNC,
	Cursors_1ScrollSE_FUNC,
	Cursors_1ScrollSW_FUNC,
	Cursors_1ScrollW_FUNC,
	Cursors_1SizeAll_FUNC,
	Cursors_1SizeNESW_FUNC,
	Cursors_1SizeNS_FUNC,
	Cursors_1SizeNWSE_FUNC,
	Cursors_1SizeWE_FUNC,
	Cursors_1UpArrow_FUNC,
	Cursors_1Wait_FUNC,
	DashStyles_1Dash_FUNC,
	DashStyles_1DashDot_FUNC,
	DashStyles_1DashDotDot_FUNC,
	DashStyles_1Dot_FUNC,
	DashStyles_1Solid_FUNC,
	DataFormats_1Rtf_FUNC,
	DataFormats_1UnicodeText_FUNC,
	DeleteGlobalRef_FUNC,
	DeleteObject_FUNC,
	DependencyObject_1ClearValue_FUNC,
	DependencyObject_1SetValue_FUNC,
	DestroyIcon_FUNC,
	DispatcherFrame_1Continue_FUNC,
	DispatcherHookEventArgs_1Operation_FUNC,
	DispatcherHooks_1DispatcherInactive_FUNC,
	DispatcherHooks_1OperationAborted_FUNC,
	DispatcherHooks_1OperationCompleted_FUNC,
	DispatcherHooks_1OperationPosted_FUNC,
	DispatcherOperation_1Priority_FUNC,
	DispatcherTimer_1Interval_FUNC,
	DispatcherTimer_1Start_FUNC,
	DispatcherTimer_1Stop_FUNC,
	DispatcherTimer_1Tag__I_FUNC,
	DispatcherTimer_1Tag__II_FUNC,
	DispatcherTimer_1Tick_FUNC,
	Dispatcher_1BeginInvoke_FUNC,
	Dispatcher_1Hooks_FUNC,
	Dispatcher_1PushFrame_FUNC,
	DockPanel_1DockProperty_FUNC,
	DockPanel_1typeid_FUNC,
	DoubleAnimationUsingKeyFrames_1KeyFrames_FUNC,
	DoubleCollection_1Add_FUNC,
	DoubleKeyFrameCollection_1Add_FUNC,
	DrawingColor_1FromArgb_FUNC,
	DrawingColor_1ToArgb_FUNC,
	DrawingContext_1Close_FUNC,
	DrawingContext_1DrawEllipse_FUNC,
	DrawingContext_1DrawGeometry_FUNC,
	DrawingContext_1DrawImage_FUNC,
	DrawingContext_1DrawLine_FUNC,
	DrawingContext_1DrawRectangle_FUNC,
	DrawingContext_1DrawRoundedRectangle_FUNC,
	DrawingContext_1DrawText_FUNC,
	DrawingContext_1Pop_FUNC,
	DrawingContext_1PushClip_FUNC,
	DrawingContext_1PushOpacity_FUNC,
	DrawingContext_1PushTransform_FUNC,
	DrawingFontFamily_1Name_FUNC,
	DrawingVisual_1RenderOpen_FUNC,
	DrawingVisual_1typeid_FUNC,
	EditingCommands_1Backspace_FUNC,
	EditingCommands_1Delete_FUNC,
	EditingCommands_1DeleteNextWord_FUNC,
	EditingCommands_1DeletePreviousWord_FUNC,
	EnableWindow_FUNC,
	Environment_1ExpandEnvironmentVariables_FUNC,
	ExecutedRoutedEventArgs_1Command_FUNC,
	ExecutedRoutedEventArgs_1Handled_FUNC,
	Expander_1Collapsed_FUNC,
	Expander_1Expanded_FUNC,
	Expander_1IsExpanded__I_FUNC,
	Expander_1IsExpanded__IZ_FUNC,
	ExtractIconExW_FUNC,
	FileDialog_1FileName_FUNC,
	FileDialog_1FileNames_FUNC,
	FileDialog_1Filter_FUNC,
	FileDialog_1InitialDirectory_FUNC,
	FileDialog_1Title_FUNC,
	FileInfo_1DirectoryName_FUNC,
	FileInfo_1Name_FUNC,
	File_1Exists_FUNC,
	FocusManager_1GetFocusScope_FUNC,
	FocusManager_1GetFocusedElement_FUNC,
	FolderBrowserDialog_1Description_FUNC,
	FolderBrowserDialog_1SelectedPath__I_FUNC,
	FolderBrowserDialog_1SelectedPath__II_FUNC,
	FontDialog_1Color__I_FUNC,
	FontDialog_1Color__II_FUNC,
	FontDialog_1Font__I_FUNC,
	FontDialog_1Font__II_FUNC,
	FontDialog_1ShowColor_FUNC,
	FontFamily_1GetTypefaces_FUNC,
	FontFamily_1Source_FUNC,
	FontStretch_1FromOpenTypeStretch_FUNC,
	FontStretch_1ToOpenTypeStretch_FUNC,
	FontStretches_1Normal_FUNC,
	FontStyles_1Italic_FUNC,
	FontStyles_1Normal_FUNC,
	FontStyles_1Oblique_FUNC,
	FontWeight_1FromOpenTypeWeight_FUNC,
	FontWeight_1ToOpenTypeWeight_FUNC,
	FontWeights_1Bold_FUNC,
	FontWeights_1Normal_FUNC,
	Font_1FontFamily_FUNC,
	Font_1Size_FUNC,
	Font_1Style_FUNC,
	Fonts_1SystemTypefaces_FUNC,
	FormattedText_1Baseline_FUNC,
	FormattedText_1BuildGeometry_FUNC,
	FormattedText_1BuildHighlightGeometry_FUNC,
	FormattedText_1Height_FUNC,
	FormattedText_1SetTextDecorations_FUNC,
	FormattedText_1WidthIncludingTrailingWhitespace_FUNC,
	FormsCommonDialog_1ShowDialog_FUNC,
	FrameworkContentElement_1Parent_FUNC,
	FrameworkContentElement_1Tag__I_FUNC,
	FrameworkContentElement_1Tag__II_FUNC,
	FrameworkContentElement_1typeid_FUNC,
	FrameworkElementFactory_1AppendChild_FUNC,
	FrameworkElementFactory_1SetBinding_FUNC,
	FrameworkElementFactory_1SetValue__III_FUNC,
	FrameworkElementFactory_1SetValue__IIZ_FUNC,
	FrameworkElementFactory_1SetValueDock_FUNC,
	FrameworkElementFactory_1SetValueOrientation_FUNC,
	FrameworkElementFactory_1SetValueStretch_FUNC,
	FrameworkElementFactory_1SetValueVerticalAlignment_FUNC,
	FrameworkElementFactory_1SetValueVisibility_FUNC,
	FrameworkElement_1ActualHeight_FUNC,
	FrameworkElement_1ActualHeightProperty_FUNC,
	FrameworkElement_1ActualWidth_FUNC,
	FrameworkElement_1ActualWidthProperty_FUNC,
	FrameworkElement_1BeginInit_FUNC,
	FrameworkElement_1BringIntoView_FUNC,
	FrameworkElement_1ContextMenu_FUNC,
	FrameworkElement_1ContextMenuClosing_FUNC,
	FrameworkElement_1ContextMenuOpening_FUNC,
	FrameworkElement_1Cursor_FUNC,
	FrameworkElement_1CursorProperty_FUNC,
	FrameworkElement_1FocusVisualStyle_FUNC,
	FrameworkElement_1GetBindingExpression_FUNC,
	FrameworkElement_1Height__I_FUNC,
	FrameworkElement_1Height__ID_FUNC,
	FrameworkElement_1HeightProperty_FUNC,
	FrameworkElement_1HorizontalAlignment_FUNC,
	FrameworkElement_1IsLoaded_FUNC,
	FrameworkElement_1Loaded_FUNC,
	FrameworkElement_1Margin__I_FUNC,
	FrameworkElement_1Margin__II_FUNC,
	FrameworkElement_1MarginProperty_FUNC,
	FrameworkElement_1MinHeight__I_FUNC,
	FrameworkElement_1MinHeight__ID_FUNC,
	FrameworkElement_1MinWidth__I_FUNC,
	FrameworkElement_1MinWidth__ID_FUNC,
	FrameworkElement_1Name_FUNC,
	FrameworkElement_1NameProperty_FUNC,
	FrameworkElement_1Parent_FUNC,
	FrameworkElement_1Resources__I_FUNC,
	FrameworkElement_1Resources__II_FUNC,
	FrameworkElement_1SizeChanged_FUNC,
	FrameworkElement_1StyleProperty_FUNC,
	FrameworkElement_1Tag__I_FUNC,
	FrameworkElement_1Tag__II_FUNC,
	FrameworkElement_1ToolTip__I_FUNC,
	FrameworkElement_1ToolTip__II_FUNC,
	FrameworkElement_1VerticalAlignment_FUNC,
	FrameworkElement_1VerticalAlignmentProperty_FUNC,
	FrameworkElement_1Width__I_FUNC,
	FrameworkElement_1Width__ID_FUNC,
	FrameworkElement_1WidthProperty_FUNC,
	FrameworkElement_1typeid_FUNC,
	FrameworkTemplate_1FindName_FUNC,
	FrameworkTemplate_1VisualTree_FUNC,
	Freezable_1CanFreeze_FUNC,
	Freezable_1Clone_FUNC,
	Freezable_1Freeze_FUNC,
	GCHandle_1Alloc_FUNC,
	GCHandle_1Dump_FUNC,
	GCHandle_1Free_FUNC,
	GeometryCollection_1Add_FUNC,
	GeometryCollection_1Clear_FUNC,
	GeometryCollection_1Count_FUNC,
	GeometryCollection_1Remove_FUNC,
	GeometryGroup_1Children__I_FUNC,
	GeometryGroup_1Children__II_FUNC,
	Geometry_1Bounds_FUNC,
	Geometry_1Clone_FUNC,
	Geometry_1FillContains_FUNC,
	Geometry_1FillContainsWithDetail_FUNC,
	Geometry_1GetFlattenedPathGeometry_FUNC,
	Geometry_1IsEmpty_FUNC,
	Geometry_1StrokeContains_FUNC,
	Geometry_1Transform__I_FUNC,
	Geometry_1Transform__II_FUNC,
	GetCursorPos_FUNC,
	GetIconInfo_FUNC,
	GetKeyboardState_FUNC,
	GetModuleHandleW_FUNC,
	GetProcessHeap_FUNC,
	GlyphRun_1BidiLevel_FUNC,
	GradientBrush_1MappingMode_FUNC,
	GradientBrush_1SpreadMethod_FUNC,
	GridViewColumnCollection_1Clear_FUNC,
	GridViewColumnCollection_1Count_FUNC,
	GridViewColumnCollection_1IndexOf_FUNC,
	GridViewColumnCollection_1Insert_FUNC,
	GridViewColumnCollection_1Remove_FUNC,
	GridViewColumnCollection_1default_FUNC,
	GridViewColumnHeader_1Content_FUNC,
	GridViewColumn_1ActualWidth_FUNC,
	GridViewColumn_1CellTemplate__I_FUNC,
	GridViewColumn_1CellTemplate__II_FUNC,
	GridViewColumn_1Header__I_FUNC,
	GridViewColumn_1Header__II_FUNC,
	GridViewColumn_1HeaderTemplate__I_FUNC,
	GridViewColumn_1HeaderTemplate__II_FUNC,
	GridViewColumn_1Width__I_FUNC,
	GridViewColumn_1Width__ID_FUNC,
	GridViewColumn_1WidthProperty_FUNC,
	GridViewHeaderRowPresenter_1typeid_FUNC,
	GridViewRowPresenterBase_1Columns_FUNC,
	GridViewRowPresenterBase_1ColumnsProperty_FUNC,
	GridViewRowPresenter_1Content__I_FUNC,
	GridViewRowPresenter_1Content__II_FUNC,
	GridViewRowPresenter_1typeid_FUNC,
	GridView_1AllowsColumnReorder_FUNC,
	GridView_1ColumnHeaderContainerStyle_FUNC,
	GridView_1Columns_FUNC,
	Grid_1ColumnDefinitions_FUNC,
	Grid_1RowDefinitions_FUNC,
	Grid_1SetColumn_FUNC,
	Grid_1SetColumnSpan_FUNC,
	Grid_1SetRow_FUNC,
	Grid_1SetRowSpan_FUNC,
	HeaderedContentControl_1Header__I_FUNC,
	HeaderedContentControl_1Header__II_FUNC,
	HeaderedItemsControl_1Header__I_FUNC,
	HeaderedItemsControl_1Header__II_FUNC,
	HeapAlloc_FUNC,
	HeapFree_FUNC,
	HwndSource_1Handle_FUNC,
	Hyperlink_1Click_FUNC,
	ICollection_1Count_FUNC,
	IEnumerable_1GetEnumerator_FUNC,
	IEnumerator_1Current_FUNC,
	IEnumerator_1MoveNext_FUNC,
	IList_1Add_FUNC,
	IList_1GetEnumerator_FUNC,
	IList_1IndexOf_FUNC,
	IList_1Insert_FUNC,
	IList_1Remove_FUNC,
	IList_1default_FUNC,
	ImageSource_1typeid_FUNC,
	Image_1Source_FUNC,
	Image_1SourceProperty_FUNC,
	Image_1Stretch_FUNC,
	Image_1StretchProperty_FUNC,
	Image_1typeid_FUNC,
	Imaging_1CreateBitmapSourceFromHIcon_FUNC,
	IndexedGlyphRunCollection_1Current_FUNC,
	IndexedGlyphRunCollection_1GetEnumerator_FUNC,
	IndexedGlyphRun_1GlyphRun_FUNC,
	IndexedGlyphRun_1TextSourceCharacterIndex_FUNC,
	IndexedGlyphRun_1TextSourceLength_FUNC,
	InlineCollection_1Add_FUNC,
	InlineCollection_1Clear_FUNC,
	InputEventArgs_1Timestamp_FUNC,
	Int32Rect_1Empty_FUNC,
	IntPtr_1ToInt32_FUNC,
	ItemCollection_1Add_FUNC,
	ItemCollection_1Clear_FUNC,
	ItemCollection_1Count_FUNC,
	ItemCollection_1CurrentItem_FUNC,
	ItemCollection_1CurrentPosition_FUNC,
	ItemCollection_1GetItemAt_FUNC,
	ItemCollection_1IndexOf_FUNC,
	ItemCollection_1Insert_FUNC,
	ItemCollection_1Remove_FUNC,
	ItemCollection_1RemoveAt_FUNC,
	ItemsControl_1HasItems_FUNC,
	ItemsControl_1IsTextSearchEnabled_FUNC,
	ItemsControl_1Items_FUNC,
	ItemsControl_1ItemsSource_FUNC,
	ItemsPresenter_1typeid_FUNC,
	JNIGetObject_FUNC,
	KeyEventArgs_1IsDown_FUNC,
	KeyEventArgs_1IsRepeat_FUNC,
	KeyEventArgs_1IsToggled_FUNC,
	KeyEventArgs_1Key_FUNC,
	KeyEventArgs_1SystemKey_FUNC,
	KeyInterop_1VirtualKeyFromKey_FUNC,
	KeyTime_1Uniform_FUNC,
	KeyboardDevice_1Modifiers_FUNC,
	KeyboardEventArgs_1KeyboardDevice_FUNC,
	KeyboardNavigation_1GetIsTabStop_FUNC,
	KeyboardNavigation_1SetControlTabNavigation_FUNC,
	KeyboardNavigation_1SetDirectionalNavigation_FUNC,
	KeyboardNavigation_1SetIsTabStop_FUNC,
	KeyboardNavigation_1SetTabNavigation_FUNC,
	Keyboard_1Focus_FUNC,
	Keyboard_1FocusedElement_FUNC,
	Keyboard_1Modifiers_FUNC,
	ListBoxItem_1IsSelected__I_FUNC,
	ListBoxItem_1IsSelected__IZ_FUNC,
	ListBox_1ScrollIntoView_FUNC,
	ListBox_1SelectAll_FUNC,
	ListBox_1SelectedItems_FUNC,
	ListBox_1SelectionMode_FUNC,
	ListBox_1UnselectAll_FUNC,
	ListView_1View_FUNC,
	LoadImage_FUNC,
	MapVirtualKeyW_FUNC,
	MatrixTransform_1Matrix__I_FUNC,
	MatrixTransform_1Matrix__II_FUNC,
	Matrix_1Invert_FUNC,
	Matrix_1IsIdentity_FUNC,
	Matrix_1M11__I_FUNC,
	Matrix_1M11__ID_FUNC,
	Matrix_1M12__I_FUNC,
	Matrix_1M12__ID_FUNC,
	Matrix_1M21__I_FUNC,
	Matrix_1M21__ID_FUNC,
	Matrix_1M22__I_FUNC,
	Matrix_1M22__ID_FUNC,
	Matrix_1Multiply_FUNC,
	Matrix_1OffsetX__I_FUNC,
	Matrix_1OffsetX__ID_FUNC,
	Matrix_1OffsetY__I_FUNC,
	Matrix_1OffsetY__ID_FUNC,
	Matrix_1RotatePrepend_FUNC,
	Matrix_1ScalePrepend_FUNC,
	Matrix_1Transform_FUNC,
	Matrix_1TranslatePrepend_FUNC,
	MenuItem_1Click_FUNC,
	MenuItem_1Icon_FUNC,
	MenuItem_1InputGestureText_FUNC,
	MenuItem_1IsCheckable_FUNC,
	MenuItem_1IsChecked__I_FUNC,
	MenuItem_1IsChecked__IZ_FUNC,
	MenuItem_1SubmenuClosed_FUNC,
	MenuItem_1SubmenuOpened_FUNC,
	Menu_1IsMainMenu_FUNC,
	MessageBox_1Show_FUNC,
	MouseButtonEventArgs_1ButtonState_FUNC,
	MouseButtonEventArgs_1ChangedButton_FUNC,
	MouseButtonEventArgs_1ClickCount_FUNC,
	MouseEventArgs_1GetPosition_FUNC,
	MouseEventArgs_1LeftButton_FUNC,
	MouseEventArgs_1MiddleButton_FUNC,
	MouseEventArgs_1RightButton_FUNC,
	MouseEventArgs_1XButton1_FUNC,
	MouseEventArgs_1XButton2_FUNC,
	MouseWheelEventArgs_1Delta_FUNC,
	Mouse_1Captured_FUNC,
	Mouse_1DirectlyOver_FUNC,
	Mouse_1GetPosition_FUNC,
	Mouse_1LeftButton_FUNC,
	Mouse_1MiddleButton_FUNC,
	Mouse_1RightButton_FUNC,
	Mouse_1SetCursor_FUNC,
	Mouse_1XButton1_FUNC,
	Mouse_1XButton2_FUNC,
	MoveMemory_FUNC,
	NewGlobalRef_FUNC,
	Object_1Equals_FUNC,
	Object_1GetType_FUNC,
	Object_1ToString_FUNC,
	OpenFileDialog_1Multiselect_FUNC,
	PROCESS_1INFORMATION_1sizeof_FUNC,
	Panel_1Background_FUNC,
	Panel_1BackgroundProperty_FUNC,
	Panel_1Children_FUNC,
	Panel_1GetZIndex_FUNC,
	Panel_1SetZIndex_FUNC,
	PasswordBox_1MaxLength__I_FUNC,
	PasswordBox_1MaxLength__II_FUNC,
	PasswordBox_1Password__I_FUNC,
	PasswordBox_1Password__II_FUNC,
	PasswordBox_1PasswordChanged_FUNC,
	PasswordBox_1PasswordChar__I_FUNC,
	PasswordBox_1PasswordChar__IC_FUNC,
	PasswordBox_1Paste_FUNC,
	PathFigureCollection_1Add_FUNC,
	PathFigureCollection_1Count_FUNC,
	PathFigure_1IsClosed__I_FUNC,
	PathFigure_1IsClosed__IZ_FUNC,
	PathFigure_1Segments__I_FUNC,
	PathFigure_1Segments__II_FUNC,
	PathFigure_1StartPoint_FUNC,
	PathGeometry_1AddGeometry_FUNC,
	PathGeometry_1Bounds_FUNC,
	PathGeometry_1Figures__I_FUNC,
	PathGeometry_1Figures__II_FUNC,
	PathGeometry_1FillRule_FUNC,
	PathSegmentCollection_1Add_FUNC,
	PathSegmentCollection_1Count_FUNC,
	Path_1Data_FUNC,
	Path_1Fill_FUNC,
	Path_1Stretch_FUNC,
	Pen_1Brush__I_FUNC,
	Pen_1Brush__II_FUNC,
	Pen_1DashCap_FUNC,
	Pen_1DashStyle_FUNC,
	Pen_1EndLineCap_FUNC,
	Pen_1LineJoin_FUNC,
	Pen_1StartLineCap_FUNC,
	Pen_1Thickness_FUNC,
	PixelFormat_1BitsPerPixel_FUNC,
	PixelFormats_1Bgr101010_FUNC,
	PixelFormats_1Bgr24_FUNC,
	PixelFormats_1Bgr32_FUNC,
	PixelFormats_1Bgr555_FUNC,
	PixelFormats_1Bgr565_FUNC,
	PixelFormats_1Bgra32_FUNC,
	PixelFormats_1BlackWhite_FUNC,
	PixelFormats_1Default_FUNC,
	PixelFormats_1Indexed1_FUNC,
	PixelFormats_1Indexed2_FUNC,
	PixelFormats_1Indexed4_FUNC,
	PixelFormats_1Indexed8_FUNC,
	PixelFormats_1Pbgra32_FUNC,
	PixelFormats_1Rgb24_FUNC,
	PointCollection_1Add_FUNC,
	Point_1X_FUNC,
	Point_1Y_FUNC,
	Popup_1AllowsTransparency_FUNC,
	Popup_1Child__I_FUNC,
	Popup_1Child__II_FUNC,
	Popup_1Closed_FUNC,
	Popup_1HorizontalOffset__I_FUNC,
	Popup_1HorizontalOffset__ID_FUNC,
	Popup_1IsOpen__I_FUNC,
	Popup_1IsOpen__IZ_FUNC,
	Popup_1Opened_FUNC,
	Popup_1VerticalOffset__I_FUNC,
	Popup_1VerticalOffset__ID_FUNC,
	PresentationSource_1CurrentSources_FUNC,
	PresentationSource_1FromVisual_FUNC,
	PresentationSource_1RootVisual_FUNC,
	ProgressBar_1IsIndeterminate__I_FUNC,
	ProgressBar_1IsIndeterminate__IZ_FUNC,
	ProgressBar_1Orientation_FUNC,
	PropertyInfo_1SetValue_FUNC,
	PropertyInfo_1SetValueBoolean_FUNC,
	RangeBase_1LargeChange__I_FUNC,
	RangeBase_1LargeChange__ID_FUNC,
	RangeBase_1Maximum__I_FUNC,
	RangeBase_1Maximum__ID_FUNC,
	RangeBase_1Minimum__I_FUNC,
	RangeBase_1Minimum__ID_FUNC,
	RangeBase_1SmallChange__I_FUNC,
	RangeBase_1SmallChange__ID_FUNC,
	RangeBase_1Value__I_FUNC,
	RangeBase_1Value__ID_FUNC,
	RangeBase_1ValueChanged_FUNC,
	Rect_1Contains_FUNC,
	Rect_1Height__I_FUNC,
	Rect_1Height__ID_FUNC,
	Rect_1Intersect_FUNC,
	Rect_1Union_FUNC,
	Rect_1Width__I_FUNC,
	Rect_1Width__ID_FUNC,
	Rect_1X__I_FUNC,
	Rect_1X__ID_FUNC,
	Rect_1Y__I_FUNC,
	Rect_1Y__ID_FUNC,
	RegistryKey_1GetSubKeyNames_FUNC,
	RegistryKey_1GetValue_FUNC,
	RegistryKey_1OpenSubKey_FUNC,
	Registry_1ClassesRoot_FUNC,
	RenderOptions_1GetBitmapScalingMode_FUNC,
	RenderOptions_1SetBitmapScalingMode_FUNC,
	RenderOptions_1SetEdgeMode_FUNC,
	RenderTargetBitmap_1Render_FUNC,
	RepeatBehavior_1Forever_FUNC,
	ResourceDictionary_1Source_FUNC,
	RoutedEventArgs_1Handled_FUNC,
	RoutedEventArgs_1OriginalSource_FUNC,
	RoutedEventArgs_1Source_FUNC,
	RoutedEventArgs_1typeid_FUNC,
	RoutedPropertyChangedEventArgs_1NewValue_FUNC,
	RoutedPropertyChangedEventArgs_1OldValue_FUNC,
	RowDefinitionCollection_1Add_FUNC,
	RowDefinition_1Height_FUNC,
	Run_1Text_FUNC,
	SHELLEXECUTEINFOW_1sizeof_FUNC,
	STARTUPINFOW_1sizeof_FUNC,
	SWTCanvas_1Visual__I_FUNC,
	SWTCanvas_1Visual__II_FUNC,
	SWTRow_1NotifyPropertyChanged_FUNC,
	SWTTextRunProperties_1ForegroundBrush_FUNC,
	ScrollBar_1Orientation__I_FUNC,
	ScrollBar_1Orientation__II_FUNC,
	ScrollBar_1Scroll_FUNC,
	ScrollBar_1ViewportSize__I_FUNC,
	ScrollBar_1ViewportSize__ID_FUNC,
	ScrollBar_1typeid_FUNC,
	ScrollEventArgs_1ScrollEventType_FUNC,
	ScrollViewer_1typeid_FUNC,
	SelectionChangedEventArgs_1AddedItems_FUNC,
	SelectionChangedEventArgs_1RemovedItems_FUNC,
	Selector_1IsSynchronizedWithCurrentItem_FUNC,
	Selector_1SelectedIndex__I_FUNC,
	Selector_1SelectedIndex__II_FUNC,
	Selector_1SelectedItem_FUNC,
	Selector_1SelectedValue_FUNC,
	Selector_1SelectionChanged_FUNC,
	SetCursorPos_FUNC,
	SetterBaseCollection_1Add_FUNC,
	Shape_1Fill_FUNC,
	Shape_1Stroke_FUNC,
	Shape_1StrokeThickness_FUNC,
	ShellExecuteExW_FUNC,
	SizeChangedEventArgs_1NewSize_FUNC,
	SizeChangedEventArgs_1PreviousSize_FUNC,
	Size_1Height__I_FUNC,
	Size_1Height__ID_FUNC,
	Size_1Width__I_FUNC,
	Size_1Width__ID_FUNC,
	Slider_1Orientation_FUNC,
	Slider_1TickFrequency_FUNC,
	Slider_1TickPlacement_FUNC,
	StackPanel_1Orientation_FUNC,
	StackPanel_1OrientationProperty_FUNC,
	StackPanel_1typeid_FUNC,
	StreamGeometryContext_1BeginFigure_FUNC,
	StreamGeometryContext_1Close_FUNC,
	StreamGeometryContext_1LineTo_FUNC,
	StreamGeometry_1Open_FUNC,
	String_1Length_FUNC,
	String_1ToCharArray_FUNC,
	Style_1Setters_FUNC,
	SystemColors_1ActiveBorderBrush_FUNC,
	SystemColors_1ActiveBorderColor_FUNC,
	SystemColors_1ActiveCaptionColor_FUNC,
	SystemColors_1ActiveCaptionTextColor_FUNC,
	SystemColors_1ControlBrush_FUNC,
	SystemColors_1ControlColor_FUNC,
	SystemColors_1ControlDarkColor_FUNC,
	SystemColors_1ControlDarkDarkColor_FUNC,
	SystemColors_1ControlLightColor_FUNC,
	SystemColors_1ControlLightLightColor_FUNC,
	SystemColors_1ControlTextBrush_FUNC,
	SystemColors_1ControlTextColor_FUNC,
	SystemColors_1GradientActiveCaptionColor_FUNC,
	SystemColors_1GradientInactiveCaptionColor_FUNC,
	SystemColors_1HighlightBrush_FUNC,
	SystemColors_1HighlightColor_FUNC,
	SystemColors_1HighlightTextColor_FUNC,
	SystemColors_1InactiveCaptionColor_FUNC,
	SystemColors_1InactiveCaptionTextColor_FUNC,
	SystemColors_1InfoColor_FUNC,
	SystemColors_1InfoTextColor_FUNC,
	SystemColors_1WindowColor_FUNC,
	SystemColors_1WindowTextColor_FUNC,
	SystemFonts_1MessageFontFamily_FUNC,
	SystemFonts_1MessageFontSize_FUNC,
	SystemFonts_1MessageFontStyle_FUNC,
	SystemFonts_1MessageFontWeight_FUNC,
	SystemParameters_1HighContrast_FUNC,
	SystemParameters_1HorizontalScrollBarButtonWidth_FUNC,
	SystemParameters_1HorizontalScrollBarHeight_FUNC,
	SystemParameters_1MinimumHorizontalDragDistance_FUNC,
	SystemParameters_1MinimumVerticalDragDistance_FUNC,
	SystemParameters_1PrimaryScreenHeight_FUNC,
	SystemParameters_1PrimaryScreenWidth_FUNC,
	SystemParameters_1ThinHorizontalBorderHeight_FUNC,
	SystemParameters_1ThinVerticalBorderWidth_FUNC,
	SystemParameters_1VerticalScrollBarButtonHeight_FUNC,
	SystemParameters_1VerticalScrollBarWidth_FUNC,
	SystemParameters_1VirtualScreenHeight_FUNC,
	SystemParameters_1VirtualScreenLeft_FUNC,
	SystemParameters_1VirtualScreenTop_FUNC,
	SystemParameters_1VirtualScreenWidth_FUNC,
	SystemParameters_1WheelScrollLines_FUNC,
	SystemParameters_1WorkArea_FUNC,
	TabControl_1TabStripPlacement_FUNC,
	TabItem_1IsSelected_FUNC,
	TextBlock_1Background_FUNC,
	TextBlock_1BackgroundProperty_FUNC,
	TextBlock_1FontFamily_FUNC,
	TextBlock_1FontFamilyProperty_FUNC,
	TextBlock_1FontSize_FUNC,
	TextBlock_1FontSizeProperty_FUNC,
	TextBlock_1FontStretch_FUNC,
	TextBlock_1FontStretchProperty_FUNC,
	TextBlock_1FontStyle_FUNC,
	TextBlock_1FontStyleProperty_FUNC,
	TextBlock_1FontWeight_FUNC,
	TextBlock_1FontWeightProperty_FUNC,
	TextBlock_1Foreground_FUNC,
	TextBlock_1ForegroundProperty_FUNC,
	TextBlock_1Inlines_FUNC,
	TextBlock_1Text_FUNC,
	TextBlock_1TextProperty_FUNC,
	TextBlock_1typeid_FUNC,
	TextBoundsCollection_1Current_FUNC,
	TextBoundsCollection_1GetEnumerator_FUNC,
	TextBounds_1Rectangle_FUNC,
	TextBoxBase_1AcceptsReturn_FUNC,
	TextBoxBase_1AcceptsTab_FUNC,
	TextBoxBase_1AppendText_FUNC,
	TextBoxBase_1Copy_FUNC,
	TextBoxBase_1Cut_FUNC,
	TextBoxBase_1HorizontalScrollBarVisibility_FUNC,
	TextBoxBase_1IsReadOnly__I_FUNC,
	TextBoxBase_1IsReadOnly__IZ_FUNC,
	TextBoxBase_1Paste_FUNC,
	TextBoxBase_1ScrollToEnd_FUNC,
	TextBoxBase_1ScrollToVerticalOffset_FUNC,
	TextBoxBase_1SelectAll_FUNC,
	TextBoxBase_1TextChanged_FUNC,
	TextBoxBase_1VerticalOffset_FUNC,
	TextBoxBase_1VerticalScrollBarVisibility_FUNC,
	TextBox_1CaretIndex__I_FUNC,
	TextBox_1CaretIndex__II_FUNC,
	TextBox_1GetFirstVisibleLineIndex_FUNC,
	TextBox_1GetLineIndexFromCharacterIndex_FUNC,
	TextBox_1GetRectFromCharacterIndex_FUNC,
	TextBox_1LineCount_FUNC,
	TextBox_1MaxLength__I_FUNC,
	TextBox_1MaxLength__II_FUNC,
	TextBox_1ScrollToLine_FUNC,
	TextBox_1Select_FUNC,
	TextBox_1SelectedText__I_FUNC,
	TextBox_1SelectedText__II_FUNC,
	TextBox_1SelectionLength__I_FUNC,
	TextBox_1SelectionLength__II_FUNC,
	TextBox_1SelectionStart__I_FUNC,
	TextBox_1SelectionStart__II_FUNC,
	TextBox_1Text__I_FUNC,
	TextBox_1Text__II_FUNC,
	TextBox_1TextWrapping_FUNC,
	TextCompositionEventArgs_1ControlText_FUNC,
	TextCompositionEventArgs_1Handled_FUNC,
	TextCompositionEventArgs_1SystemText_FUNC,
	TextCompositionEventArgs_1Text_FUNC,
	TextDecorationCollection_1Add_FUNC,
	TextDecorations_1Strikethrough_FUNC,
	TextDecorations_1Underline_FUNC,
	TextFormatter_1Create_FUNC,
	TextFormatter_1FormatLine_FUNC,
	TextLine_1Baseline_FUNC,
	TextLine_1Draw_FUNC,
	TextLine_1GetCharacterHitFromDistance_FUNC,
	TextLine_1GetDistanceFromCharacterHit_FUNC,
	TextLine_1GetIndexedGlyphRuns_FUNC,
	TextLine_1GetNextCaretCharacterHit_FUNC,
	TextLine_1GetPreviousCaretCharacterHit_FUNC,
	TextLine_1GetTextBounds_FUNC,
	TextLine_1GetTextLineBreak_FUNC,
	TextLine_1Height_FUNC,
	TextLine_1Length_FUNC,
	TextLine_1NewlineLength_FUNC,
	TextLine_1Start_FUNC,
	TextLine_1Width_FUNC,
	TextLine_1WidthIncludingTrailingWhitespace_FUNC,
	TextTabPropertiesCollection_1Add_FUNC,
	Thickness_1Bottom_FUNC,
	Thickness_1Left_FUNC,
	Thickness_1Right_FUNC,
	Thickness_1Top_FUNC,
	TileBrush_1AlignmentX_FUNC,
	TileBrush_1AlignmentY_FUNC,
	TileBrush_1Stretch_FUNC,
	TileBrush_1TileMode_FUNC,
	TileBrush_1Viewport_FUNC,
	TileBrush_1ViewportUnits_FUNC,
	TimeSpan_1FromMilliseconds_FUNC,
	Timeline_1AutoReverse_FUNC,
	Timeline_1Duration_FUNC,
	Timeline_1RepeatBehavior_FUNC,
	ToUnicode_FUNC,
	ToggleButton_1Checked_FUNC,
	ToggleButton_1CheckedEvent_FUNC,
	ToggleButton_1IndeterminateEvent_FUNC,
	ToggleButton_1IsChecked__I_FUNC,
	ToggleButton_1IsChecked__IZ_FUNC,
	ToggleButton_1IsCheckedNullSetter_FUNC,
	ToggleButton_1IsCheckedProperty_FUNC,
	ToggleButton_1IsThreeStateProperty_FUNC,
	ToggleButton_1Unchecked_FUNC,
	ToggleButton_1UncheckedEvent_FUNC,
	ToolBarTray_1Background_FUNC,
	ToolBarTray_1IsLocked__I_FUNC,
	ToolBarTray_1IsLocked__IZ_FUNC,
	ToolBarTray_1Orientation_FUNC,
	ToolBarTray_1ToolBars_FUNC,
	ToolBar_1Band__I_FUNC,
	ToolBar_1Band__II_FUNC,
	ToolBar_1BandIndex__I_FUNC,
	ToolBar_1BandIndex__II_FUNC,
	ToolBar_1HasOverflowItems_FUNC,
	ToolBar_1SetOverflowMode_FUNC,
	ToolBar_1typeid_FUNC,
	TransformCollection_1Add_FUNC,
	TransformGroup_1Children_FUNC,
	Transform_1Clone_FUNC,
	TreeViewItem_1CollapsedEvent_FUNC,
	TreeViewItem_1ExpandedEvent_FUNC,
	TreeViewItem_1IsExpanded__I_FUNC,
	TreeViewItem_1IsExpanded__IZ_FUNC,
	TreeViewItem_1IsSelected__I_FUNC,
	TreeViewItem_1IsSelected__IZ_FUNC,
	TreeView_1SelectedItem_FUNC,
	TreeView_1SelectedItemChanged_FUNC,
	TreeView_1typeid_FUNC,
	TypeConverter_1ConvertFromString_FUNC,
	TypeConverter_1ConvertToString_FUNC,
	TypeDescriptor_1GetConverter_FUNC,
	Type_1FullName_FUNC,
	Type_1GetProperty_FUNC,
	Type_1IsInstanceOfType_FUNC,
	TypefaceCollection_1Count_FUNC,
	TypefaceCollection_1Current_FUNC,
	TypefaceCollection_1GetEnumerator_FUNC,
	Typeface_1FontFamily_FUNC,
	Typeface_1Stretch_FUNC,
	Typeface_1Style_FUNC,
	Typeface_1Weight_FUNC,
	UIElementCollection_1Add_FUNC,
	UIElementCollection_1Clear_FUNC,
	UIElementCollection_1Contains_FUNC,
	UIElementCollection_1Count_FUNC,
	UIElementCollection_1Current_FUNC,
	UIElementCollection_1GetEnumerator_FUNC,
	UIElementCollection_1IndexOf_FUNC,
	UIElementCollection_1Insert_FUNC,
	UIElementCollection_1Remove_FUNC,
	UIElementCollection_1default_FUNC,
	UIElement_1AddHandler_FUNC,
	UIElement_1BeginAnimation_FUNC,
	UIElement_1CaptureMouse_FUNC,
	UIElement_1Clip_FUNC,
	UIElement_1ClipToBounds_FUNC,
	UIElement_1DesiredSize_FUNC,
	UIElement_1Focus_FUNC,
	UIElement_1Focusable_FUNC,
	UIElement_1InputHitTest_FUNC,
	UIElement_1InvalidateVisual_FUNC,
	UIElement_1IsEnabled__I_FUNC,
	UIElement_1IsEnabled__IZ_FUNC,
	UIElement_1IsFocused_FUNC,
	UIElement_1IsHitTestVisible_FUNC,
	UIElement_1IsKeyboardFocusWithin_FUNC,
	UIElement_1IsKeyboardFocused_FUNC,
	UIElement_1IsMeasureValid_FUNC,
	UIElement_1IsMouseOver_FUNC,
	UIElement_1IsVisible_FUNC,
	UIElement_1KeyDown_FUNC,
	UIElement_1KeyUp_FUNC,
	UIElement_1LostKeyboardFocus_FUNC,
	UIElement_1Measure_FUNC,
	UIElement_1MouseDown_FUNC,
	UIElement_1MouseEnter_FUNC,
	UIElement_1MouseLeave_FUNC,
	UIElement_1MouseMove_FUNC,
	UIElement_1MouseUp_FUNC,
	UIElement_1MouseWheel_FUNC,
	UIElement_1MoveFocus_FUNC,
	UIElement_1OpacityProperty_FUNC,
	UIElement_1PreviewGotKeyboardFocus_FUNC,
	UIElement_1PreviewKeyDown_FUNC,
	UIElement_1PreviewKeyUp_FUNC,
	UIElement_1PreviewLostKeyboardFocus_FUNC,
	UIElement_1PreviewMouseDown_FUNC,
	UIElement_1PreviewMouseMove_FUNC,
	UIElement_1PreviewMouseUp_FUNC,
	UIElement_1PreviewMouseWheel_FUNC,
	UIElement_1PreviewTextInput_FUNC,
	UIElement_1ReleaseMouseCapture_FUNC,
	UIElement_1SnapsToDevicePixels_FUNC,
	UIElement_1TextInput_FUNC,
	UIElement_1TranslatePoint_FUNC,
	UIElement_1UpdateLayout_FUNC,
	UIElement_1Visibility__I_FUNC,
	UIElement_1Visibility__IB_FUNC,
	UIElement_1VisibilityProperty_FUNC,
	VirtualizingStackPanel_1VerticalOffset_FUNC,
	VisualTreeHelper_1GetChild_FUNC,
	VisualTreeHelper_1GetChildrenCount_FUNC,
	VisualTreeHelper_1GetParent_FUNC,
	Visual_1IsAncestorOf_FUNC,
	Visual_1IsDescendantOf_FUNC,
	Visual_1PointFromScreen_FUNC,
	Visual_1PointToScreen_FUNC,
	WindowCollection_1Count_FUNC,
	WindowCollection_1Current_FUNC,
	WindowCollection_1GetEnumerator_FUNC,
	Window_1Activate_FUNC,
	Window_1Activated_FUNC,
	Window_1AllowsTransparency_FUNC,
	Window_1Close_FUNC,
	Window_1Closing_FUNC,
	Window_1Deactivated_FUNC,
	Window_1GetWindow_FUNC,
	Window_1Hide_FUNC,
	Window_1Icon_FUNC,
	Window_1IsActive_FUNC,
	Window_1Left__I_FUNC,
	Window_1Left__ID_FUNC,
	Window_1LocationChanged_FUNC,
	Window_1Owner_FUNC,
	Window_1ResizeMode_FUNC,
	Window_1Show_FUNC,
	Window_1ShowInTaskbar_FUNC,
	Window_1Title__I_FUNC,
	Window_1Title__II_FUNC,
	Window_1Top__I_FUNC,
	Window_1Top__ID_FUNC,
	Window_1Topmost_FUNC,
	Window_1WindowState__I_FUNC,
	Window_1WindowState__II_FUNC,
	Window_1WindowStyle_FUNC,
	WriteableBitmap_1WritePixels_FUNC,
	XamlReader_1Load_FUNC,
	XmlReader_1Create_FUNC,
	gcnew_1AccessText_FUNC,
	gcnew_1Application_FUNC,
	gcnew_1ArcSegment_FUNC,
	gcnew_1ArrayList_FUNC,
	gcnew_1BezierSegment_FUNC,
	gcnew_1Binding___FUNC,
	gcnew_1Binding__I_FUNC,
	gcnew_1Bitmap_FUNC,
	gcnew_1BitmapImage_FUNC,
	gcnew_1BitmapPalette_FUNC,
	gcnew_1Button_FUNC,
	gcnew_1CancelEventHandler_FUNC,
	gcnew_1Canvas_FUNC,
	gcnew_1CharacterHit_FUNC,
	gcnew_1CheckBox_FUNC,
	gcnew_1ColorDialog_FUNC,
	gcnew_1ColorList_FUNC,
	gcnew_1ColumnDefinition_FUNC,
	gcnew_1CombinedGeometry_FUNC,
	gcnew_1ComboBox_FUNC,
	gcnew_1ComboBoxItem_FUNC,
	gcnew_1CompositeCollection_FUNC,
	gcnew_1ContentControl_FUNC,
	gcnew_1ContextMenu_FUNC,
	gcnew_1ContextMenuEventHandler_FUNC,
	gcnew_1ControlTemplate_FUNC,
	gcnew_1CroppedBitmap_FUNC,
	gcnew_1DashStyle_FUNC,
	gcnew_1DataTemplate_FUNC,
	gcnew_1DiscreteDoubleKeyFrame_FUNC,
	gcnew_1DispatcherFrame_FUNC,
	gcnew_1DispatcherHookEventHandler_FUNC,
	gcnew_1DispatcherTimer_FUNC,
	gcnew_1DoubleAnimationUsingKeyFrames_FUNC,
	gcnew_1DoubleCollection_FUNC,
	gcnew_1DrawingVisual_FUNC,
	gcnew_1Duration_FUNC,
	gcnew_1EllipseGeometry_FUNC,
	gcnew_1EventHandler_FUNC,
	gcnew_1ExecutedRoutedEventHandler_FUNC,
	gcnew_1Expander_FUNC,
	gcnew_1FileInfo_FUNC,
	gcnew_1FolderBrowserDialog_FUNC,
	gcnew_1Font_FUNC,
	gcnew_1FontDialog_FUNC,
	gcnew_1FontFamily_FUNC,
	gcnew_1FormatConvertedBitmap_FUNC,
	gcnew_1FormattedText_FUNC,
	gcnew_1FrameworkElementFactory__I_FUNC,
	gcnew_1FrameworkElementFactory__II_FUNC,
	gcnew_1GeometryGroup_FUNC,
	gcnew_1Grid_FUNC,
	gcnew_1GridLength_FUNC,
	gcnew_1GridView_FUNC,
	gcnew_1GridViewColumn_FUNC,
	gcnew_1GridViewColumnCollection_FUNC,
	gcnew_1GridViewColumnHeader_FUNC,
	gcnew_1GroupBox_FUNC,
	gcnew_1Hyperlink_FUNC,
	gcnew_1Image_FUNC,
	gcnew_1ImageBrush_FUNC,
	gcnew_1Int32_FUNC,
	gcnew_1Int32Rect_FUNC,
	gcnew_1IntPtr_FUNC,
	gcnew_1KeyEventHandler_FUNC,
	gcnew_1KeyboardFocusChangedEventHandler_FUNC,
	gcnew_1Label_FUNC,
	gcnew_1LineSegment_FUNC,
	gcnew_1LinearGradientBrush__IID_FUNC,
	gcnew_1LinearGradientBrush__IIII_FUNC,
	gcnew_1ListBox_FUNC,
	gcnew_1ListBoxItem_FUNC,
	gcnew_1ListView_FUNC,
	gcnew_1ListViewItem_FUNC,
	gcnew_1Matrix_FUNC,
	gcnew_1MatrixTransform_FUNC,
	gcnew_1MemoryStream_FUNC,
	gcnew_1Menu_FUNC,
	gcnew_1MenuItem_FUNC,
	gcnew_1MouseButtonEventHandler_FUNC,
	gcnew_1MouseEventHandler_FUNC,
	gcnew_1MouseWheelEventHandler_FUNC,
	gcnew_1NoArgsDelegate_FUNC,
	gcnew_1OpenFileDialog_FUNC,
	gcnew_1PasswordBox_FUNC,
	gcnew_1Path_FUNC,
	gcnew_1PathFigure_FUNC,
	gcnew_1PathGeometry_FUNC,
	gcnew_1Pen_FUNC,
	gcnew_1Point_FUNC,
	gcnew_1PointCollection_FUNC,
	gcnew_1PolyLineSegment_FUNC,
	gcnew_1Popup_FUNC,
	gcnew_1ProgressBar_FUNC,
	gcnew_1QuadraticBezierSegment_FUNC,
	gcnew_1RadioButton_FUNC,
	gcnew_1Rect_FUNC,
	gcnew_1Rectangle_FUNC,
	gcnew_1RectangleGeometry_FUNC,
	gcnew_1RenderTargetBitmap_FUNC,
	gcnew_1RepeatButton_FUNC,
	gcnew_1ResourceDictionary_FUNC,
	gcnew_1RoutedEventHandler_FUNC,
	gcnew_1RoutedPropertyChangedEventHandler_FUNC,
	gcnew_1RoutedPropertyChangedEventHandlerObject_FUNC,
	gcnew_1RowDefinition_FUNC,
	gcnew_1Run_FUNC,
	gcnew_1SWTCanvas_FUNC,
	gcnew_1SWTCellConverter_FUNC,
	gcnew_1SWTRow_FUNC,
	gcnew_1SWTSafeHandle_FUNC,
	gcnew_1SWTTextEmbeddedObject_FUNC,
	gcnew_1SWTTextParagraphProperties_FUNC,
	gcnew_1SWTTextRunProperties_FUNC,
	gcnew_1SWTTextSource_FUNC,
	gcnew_1SWTTreeView_FUNC,
	gcnew_1SWTTreeViewRowPresenter_FUNC,
	gcnew_1SaveFileDialog_FUNC,
	gcnew_1ScaleTransform_FUNC,
	gcnew_1ScrollBar_FUNC,
	gcnew_1ScrollEventHandler_FUNC,
	gcnew_1SelectionChangedEventHandler_FUNC,
	gcnew_1Separator_FUNC,
	gcnew_1Setter_FUNC,
	gcnew_1Size___FUNC,
	gcnew_1Size__DD_FUNC,
	gcnew_1SizeChangedEventHandler_FUNC,
	gcnew_1Slider_FUNC,
	gcnew_1SolidColorBrush_FUNC,
	gcnew_1StackPanel_FUNC,
	gcnew_1StreamGeometry_FUNC,
	gcnew_1String_FUNC,
	gcnew_1StringReader_FUNC,
	gcnew_1Style_FUNC,
	gcnew_1TabControl_FUNC,
	gcnew_1TabItem_FUNC,
	gcnew_1TextBlock_FUNC,
	gcnew_1TextBox_FUNC,
	gcnew_1TextChangedEventHandler_FUNC,
	gcnew_1TextCharacters_FUNC,
	gcnew_1TextCompositionEventHandler_FUNC,
	gcnew_1TextDecorationCollection_FUNC,
	gcnew_1TextEndOfLine_FUNC,
	gcnew_1TextEndOfParagraph_FUNC,
	gcnew_1TextTabProperties_FUNC,
	gcnew_1TextTabPropertiesCollection_FUNC,
	gcnew_1Thickness_FUNC,
	gcnew_1TiffBitmapEncoder_FUNC,
	gcnew_1TimeSpan_FUNC,
	gcnew_1TimerHandler_FUNC,
	gcnew_1ToggleButton_FUNC,
	gcnew_1ToolBar_FUNC,
	gcnew_1ToolBarTray_FUNC,
	gcnew_1TransformGroup_FUNC,
	gcnew_1TranslateTransform_FUNC,
	gcnew_1TraversalRequest_FUNC,
	gcnew_1TreeView_FUNC,
	gcnew_1TreeViewItem_FUNC,
	gcnew_1Typeface_FUNC,
	gcnew_1Uri_FUNC,
	gcnew_1UserControl_FUNC,
	gcnew_1Window_FUNC,
	gcnew_1WriteableBitmap__I_FUNC,
	gcnew_1WriteableBitmap__IIDDII_FUNC,
	memcpy_FUNC,
} OS_FUNCS;

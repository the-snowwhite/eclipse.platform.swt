/*******************************************************************************
 * Copyright (c) 2000, 2016 IBM Corporation and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *     IBM Corporation - initial API and implementation
 *******************************************************************************/
package org.eclipse.swt.widgets;


import org.eclipse.swt.*;
import org.eclipse.swt.events.*;
import org.eclipse.swt.graphics.*;
import org.eclipse.swt.internal.*;
import org.eclipse.swt.internal.gtk.*;

/**
 * Instances of this class represent a selectable user interface
 * object that displays a list of strings and issues notification
 * when a string is selected.  A list may be single or multi select.
 * <p>
 * <dl>
 * <dt><b>Styles:</b></dt>
 * <dd>SINGLE, MULTI</dd>
 * <dt><b>Events:</b></dt>
 * <dd>Selection, DefaultSelection</dd>
 * </dl>
 * <p>
 * Note: Only one of SINGLE and MULTI may be specified.
 * </p><p>
 * IMPORTANT: This class is <em>not</em> intended to be subclassed.
 * </p>
 *
 * @see <a href="http://www.eclipse.org/swt/snippets/#list">List snippets</a>
 * @see <a href="http://www.eclipse.org/swt/examples.php">SWT Example: ControlExample</a>
 * @see <a href="http://www.eclipse.org/swt/">Sample code and further information</a>
 * @noextend This class is not intended to be subclassed by clients.
 */
public class List extends Scrollable {
	long /*int*/ modelHandle;
	int topIndex;

	static final int TEXT_COLUMN = 0;
	GdkRGBA background;
	double cachedAdjustment, currentAdjustment;

/**
 * Constructs a new instance of this class given its parent
 * and a style value describing its behavior and appearance.
 * <p>
 * The style value is either one of the style constants defined in
 * class <code>SWT</code> which is applicable to instances of this
 * class, or must be built by <em>bitwise OR</em>'ing together
 * (that is, using the <code>int</code> "|" operator) two or more
 * of those <code>SWT</code> style constants. The class description
 * lists the style constants that are applicable to the class.
 * Style bits are also inherited from superclasses.
 * </p>
 *
 * @param parent a composite control which will be the parent of the new instance (cannot be null)
 * @param style the style of control to construct
 *
 * @exception IllegalArgumentException <ul>
 *    <li>ERROR_NULL_ARGUMENT - if the parent is null</li>
 * </ul>
 * @exception SWTException <ul>
 *    <li>ERROR_THREAD_INVALID_ACCESS - if not called from the thread that created the parent</li>
 *    <li>ERROR_INVALID_SUBCLASS - if this class is not an allowed subclass</li>
 * </ul>
 *
 * @see SWT#SINGLE
 * @see SWT#MULTI
 * @see Widget#checkSubclass
 * @see Widget#getStyle
 */
public List (Composite parent, int style) {
	super (parent, checkStyle (style));
}

/**
 * Adds the argument to the end of the receiver's list.
 * <p>
 * Note: If control characters like '\n', '\t' etc. are used
 * in the string, then the behavior is platform dependent.
 * </p>
 * @param string the new item
 *
 * @exception IllegalArgumentException <ul>
 *    <li>ERROR_NULL_ARGUMENT - if the string is null</li>
 * </ul>
 * @exception SWTException <ul>
 *    <li>ERROR_WIDGET_DISPOSED - if the receiver has been disposed</li>
 *    <li>ERROR_THREAD_INVALID_ACCESS - if not called from the thread that created the receiver</li>
 * </ul>
 *
 * @see #add(String,int)
 */
public void add (String string) {
	checkWidget();
	if (string == null) error (SWT.ERROR_NULL_ARGUMENT);
	byte [] buffer = Converter.wcsToMbcs (string, true);
	long /*int*/ iter = OS.g_malloc (OS.GtkTreeIter_sizeof ());
	if (iter == 0) error (SWT.ERROR_ITEM_NOT_ADDED);
	OS.gtk_list_store_append (modelHandle, iter);
	OS.gtk_list_store_set (modelHandle, iter, TEXT_COLUMN, buffer, -1);
	OS.g_free (iter);
}

/**
 * Adds the argument to the receiver's list at the given
 * zero-relative index.
 * <p>
 * Note: To add an item at the end of the list, use the
 * result of calling <code>getItemCount()</code> as the
 * index or use <code>add(String)</code>.
 * </p><p>
 * Also note, if control characters like '\n', '\t' etc. are used
 * in the string, then the behavior is platform dependent.
 * </p>
 *
 * @param string the new item
 * @param index the index for the item
 *
 * @exception IllegalArgumentException <ul>
 *    <li>ERROR_NULL_ARGUMENT - if the string is null</li>
 *    <li>ERROR_INVALID_RANGE - if the index is not between 0 and the number of elements in the list (inclusive)</li>
 * </ul>
 * @exception SWTException <ul>
 *    <li>ERROR_WIDGET_DISPOSED - if the receiver has been disposed</li>
 *    <li>ERROR_THREAD_INVALID_ACCESS - if not called from the thread that created the receiver</li>
 * </ul>
 *
 * @see #add(String)
 */
public void add (String string, int index) {
	checkWidget();
	if (string == null) error (SWT.ERROR_NULL_ARGUMENT);
	int count = OS.gtk_tree_model_iter_n_children (modelHandle, 0);
	if (!(0 <= index && index <= count)) {
		error (SWT.ERROR_INVALID_RANGE);
	}
	byte [] buffer = Converter.wcsToMbcs (string, true);
	long /*int*/ iter = OS.g_malloc (OS.GtkTreeIter_sizeof ());
	if (iter == 0) error (SWT.ERROR_ITEM_NOT_ADDED);
	/*
	* Feature in GTK.  It is much faster to append to a list store
	* than to insert at the end using gtk_list_store_insert().
	*/
	if (index == count) {
		OS.gtk_list_store_append (modelHandle, iter);
	} else {
		OS.gtk_list_store_insert (modelHandle, iter, index);
	}
	OS.gtk_list_store_set (modelHandle, iter, TEXT_COLUMN, buffer, -1);
	OS.g_free (iter);
}

/**
 * Adds the listener to the collection of listeners who will
 * be notified when the user changes the receiver's selection, by sending
 * it one of the messages defined in the <code>SelectionListener</code>
 * interface.
 * <p>
 * <code>widgetSelected</code> is called when the selection changes.
 * <code>widgetDefaultSelected</code> is typically called when an item is double-clicked.
 * </p>
 *
 * @param listener the listener which should be notified when the user changes the receiver's selection
 *
 * @exception IllegalArgumentException <ul>
 *    <li>ERROR_NULL_ARGUMENT - if the listener is null</li>
 * </ul>
 * @exception SWTException <ul>
 *    <li>ERROR_WIDGET_DISPOSED - if the receiver has been disposed</li>
 *    <li>ERROR_THREAD_INVALID_ACCESS - if not called from the thread that created the receiver</li>
 * </ul>
 *
 * @see SelectionListener
 * @see #removeSelectionListener
 * @see SelectionEvent
 */
public void addSelectionListener(SelectionListener listener) {
	checkWidget();
	if (listener == null) error (SWT.ERROR_NULL_ARGUMENT);
	TypedListener typedListener = new TypedListener (listener);
	addListener (SWT.Selection,typedListener);
	addListener (SWT.DefaultSelection,typedListener);
}

static int checkStyle (int style) {
	return checkBits (style, SWT.SINGLE, SWT.MULTI, 0, 0, 0, 0);
}

@Override
void createHandle (int index) {
	state |= HANDLE;
	fixedHandle = OS.g_object_new (display.gtk_fixed_get_type (), 0);
	if (fixedHandle == 0) error (SWT.ERROR_NO_HANDLES);
	OS.gtk_widget_set_has_window (fixedHandle, true);
	scrolledHandle = OS.gtk_scrolled_window_new (0, 0);
	if (scrolledHandle == 0) error (SWT.ERROR_NO_HANDLES);
	/*
	* Columns:
	* 0 - text
	*/
	long /*int*/ [] types = new long /*int*/ [] {OS.G_TYPE_STRING ()};
	modelHandle = OS.gtk_list_store_newv (types.length, types);
	if (modelHandle == 0) error (SWT.ERROR_NO_HANDLES);
	handle = OS.gtk_tree_view_new_with_model (modelHandle);
	if (handle == 0) error (SWT.ERROR_NO_HANDLES);
	long /*int*/ textRenderer = OS.gtk_cell_renderer_text_new ();
	if (textRenderer == 0) error (SWT.ERROR_NO_HANDLES);
	long /*int*/ columnHandle = OS.gtk_tree_view_column_new ();
	if (columnHandle == 0) error (SWT.ERROR_NO_HANDLES);
	OS.gtk_tree_view_column_pack_start (columnHandle, textRenderer, true);
	OS.gtk_tree_view_column_add_attribute (columnHandle, textRenderer, OS.text, TEXT_COLUMN);
	OS.gtk_tree_view_column_set_min_width (columnHandle, 0);
	OS.gtk_tree_view_insert_column (handle, columnHandle, index);
	OS.gtk_container_add (fixedHandle, scrolledHandle);
	OS.gtk_container_add (scrolledHandle, handle);

	int mode = (style & SWT.MULTI) != 0 ? OS.GTK_SELECTION_MULTIPLE : OS.GTK_SELECTION_BROWSE;
	long /*int*/ selectionHandle = OS.gtk_tree_view_get_selection (handle);
	OS.gtk_tree_selection_set_mode (selectionHandle, mode);
	OS.gtk_tree_view_set_headers_visible (handle, false);
	int hsp = (style & SWT.H_SCROLL) != 0 ? OS.GTK_POLICY_AUTOMATIC : OS.GTK_POLICY_NEVER;
	int vsp = (style & SWT.V_SCROLL) != 0 ? OS.GTK_POLICY_AUTOMATIC : OS.GTK_POLICY_NEVER;
	OS.gtk_scrolled_window_set_policy (scrolledHandle, hsp, vsp);
	if ((style & SWT.BORDER) != 0) OS.gtk_scrolled_window_set_shadow_type (scrolledHandle, OS.GTK_SHADOW_ETCHED_IN);
	/*
	* Bug in GTK. When a treeview is the child of an override shell,
	* and if the user has ever invokes the interactive search field,
	* and the treeview is disposed on a focus out event, it segment
	* faults. The fix is to disable the search field in an override
	* shell.
	*/
	if ((getShell ().style & SWT.ON_TOP) != 0) {
		OS.gtk_tree_view_set_search_column (handle, -1);
	}
	// In GTK 3 font description is inherited from parent widget which is not how SWT has always worked,
	// reset to default font to get the usual behavior
	if (OS.GTK3) {
		setFontDescription(defaultFont().handle);
	}
}

@Override
int applyThemeBackground () {
	return -1; /* No Change */
}

@Override
Point computeSizeInPixels (int wHint, int hHint, boolean changed) {
	checkWidget ();
	if (wHint != SWT.DEFAULT && wHint < 0) wHint = 0;
	if (hHint != SWT.DEFAULT && hHint < 0) hHint = 0;
	OS.gtk_widget_realize(handle);
	Point size = computeNativeSize (handle, wHint, hHint, changed);
	if (size.x == 0 && wHint == SWT.DEFAULT) size.x = DEFAULT_WIDTH;
	/*
	 * in GTK 3.8 computeNativeSize returning 0 for height.
	 * So if the height is returned as zero calculate the table height
	 * based on the number of items in the table
	 */
	 if (OS.GTK3 && size.y == 0 && hHint == SWT.DEFAULT) {
	 size.y = getItemCount() * getItemHeightInPixels();
	 }

	 /*
	 * In case the table doesn't contain any elements,
	 * getItemCount returns 0 and size.y will be 0
	 * so need to assign default height
	 */
	if (size.y == 0 && hHint == SWT.DEFAULT) size.y = DEFAULT_HEIGHT;
	Rectangle trim = computeTrimInPixels (0, 0, size.x, size.y);
	size.x = trim.width;
	size.y = trim.height;
	return size;
}

@Override
void deregister() {
	super.deregister ();
	display.removeWidget (OS.gtk_tree_view_get_selection (handle));
}

/**
 * Deselects the item at the given zero-relative index in the receiver.
 * If the item at the index was already deselected, it remains
 * deselected. Indices that are out of range are ignored.
 *
 * @param index the index of the item to deselect
 *
 * @exception SWTException <ul>
 *    <li>ERROR_WIDGET_DISPOSED - if the receiver has been disposed</li>
 *    <li>ERROR_THREAD_INVALID_ACCESS - if not called from the thread that created the receiver</li>
 * </ul>
 */
public void deselect (int index) {
	checkWidget();
	if (!(0 <= index && index < OS.gtk_tree_model_iter_n_children (modelHandle, 0)))  return;
	long /*int*/ iter = OS.g_malloc (OS.GtkTreeIter_sizeof ());
	long /*int*/ selection = OS.gtk_tree_view_get_selection (handle);
	OS.g_signal_handlers_block_matched (selection, OS.G_SIGNAL_MATCH_DATA, 0, 0, 0, 0, CHANGED);
	OS.gtk_tree_model_iter_nth_child (modelHandle, iter, 0, index);
	OS.gtk_tree_selection_unselect_iter (selection, iter);
	OS.g_signal_handlers_unblock_matched (selection, OS.G_SIGNAL_MATCH_DATA, 0, 0, 0, 0, CHANGED);
	OS.g_free (iter);
}

/**
 * Deselects the items at the given zero-relative indices in the receiver.
 * If the item at the given zero-relative index in the receiver
 * is selected, it is deselected.  If the item at the index
 * was not selected, it remains deselected.  The range of the
 * indices is inclusive. Indices that are out of range are ignored.
 *
 * @param start the start index of the items to deselect
 * @param end the end index of the items to deselect
 *
 * @exception SWTException <ul>
 *    <li>ERROR_WIDGET_DISPOSED - if the receiver has been disposed</li>
 *    <li>ERROR_THREAD_INVALID_ACCESS - if not called from the thread that created the receiver</li>
 * </ul>
 */
public void deselect (int start, int end) {
	checkWidget();
	if (start < 0 && end < 0) return;
	int count = OS.gtk_tree_model_iter_n_children (modelHandle, 0);
	if (start >= count && end >= count) return;
	start = Math.min (count - 1, Math.max (0, start));
	end = Math.min (count - 1, Math.max (0, end));
	long /*int*/ iter = OS.g_malloc (OS.GtkTreeIter_sizeof ());
	long /*int*/ selection = OS.gtk_tree_view_get_selection (handle);
	OS.g_signal_handlers_block_matched (selection, OS.G_SIGNAL_MATCH_DATA, 0, 0, 0, 0, CHANGED);
	for (int index=start; index<=end; index++) {
		OS.gtk_tree_model_iter_nth_child (modelHandle, iter, 0, index);
		OS.gtk_tree_selection_unselect_iter (selection, iter);
	}
	OS.g_signal_handlers_unblock_matched (selection, OS.G_SIGNAL_MATCH_DATA, 0, 0, 0, 0, CHANGED);
	OS.g_free (iter);
}

/**
 * Deselects the items at the given zero-relative indices in the receiver.
 * If the item at the given zero-relative index in the receiver
 * is selected, it is deselected.  If the item at the index
 * was not selected, it remains deselected. Indices that are out
 * of range and duplicate indices are ignored.
 *
 * @param indices the array of indices for the items to deselect
 *
 * @exception IllegalArgumentException <ul>
 *    <li>ERROR_NULL_ARGUMENT - if the set of indices is null</li>
 * </ul>
 * @exception SWTException <ul>
 *    <li>ERROR_WIDGET_DISPOSED - if the receiver has been disposed</li>
 *    <li>ERROR_THREAD_INVALID_ACCESS - if not called from the thread that created the receiver</li>
 * </ul>
 */
public void deselect (int [] indices) {
	checkWidget();
	if (indices == null) error (SWT.ERROR_NULL_ARGUMENT);
	long /*int*/ iter = OS.g_malloc (OS.GtkTreeIter_sizeof ());
	int count = OS.gtk_tree_model_iter_n_children (modelHandle, 0);
	long /*int*/ selection = OS.gtk_tree_view_get_selection (handle);
	OS.g_signal_handlers_block_matched (selection, OS.G_SIGNAL_MATCH_DATA, 0, 0, 0, 0, CHANGED);
	for (int i=0; i<indices.length; i++) {
		int index = indices [i];
		if (index < 0 || index > count - 1) continue;
		OS.gtk_tree_model_iter_nth_child (modelHandle, iter, 0, index);
		OS.gtk_tree_selection_unselect_iter (selection, iter);
	}
	OS.g_signal_handlers_unblock_matched (selection, OS.G_SIGNAL_MATCH_DATA, 0, 0, 0, 0, CHANGED);
	OS.g_free (iter);
}

/**
 * Deselects all selected items in the receiver.
 *
 * @exception SWTException <ul>
 *    <li>ERROR_WIDGET_DISPOSED - if the receiver has been disposed</li>
 *    <li>ERROR_THREAD_INVALID_ACCESS - if not called from the thread that created the receiver</li>
 * </ul>
 */
public void deselectAll () {
	checkWidget();
	long /*int*/ selection = OS.gtk_tree_view_get_selection (handle);
	OS.g_signal_handlers_block_matched (selection, OS.G_SIGNAL_MATCH_DATA, 0, 0, 0, 0, CHANGED);
	OS.gtk_tree_selection_unselect_all (selection);
	OS.g_signal_handlers_unblock_matched (selection, OS.G_SIGNAL_MATCH_DATA, 0, 0, 0, 0, CHANGED);
}

@Override
boolean dragDetect (int x, int y, boolean filter, boolean dragOnTimeout, boolean [] consume) {
	boolean selected = false;
	if (filter) {
		long /*int*/ [] path = new long /*int*/ [1];
		if (OS.gtk_tree_view_get_path_at_pos (handle, x, y, path, null, null, null)) {
			if (path [0] != 0) {
				long /*int*/ selection = OS.gtk_tree_view_get_selection (handle);
				if (OS.gtk_tree_selection_path_is_selected (selection, path [0])) selected = true;
				OS.gtk_tree_path_free (path [0]);
			}
		} else {
			return false;
		}
	}
	boolean dragDetect = super.dragDetect (x, y, filter, false, consume);
	if (dragDetect && selected && consume != null) consume [0] = true;
	return dragDetect;
}

@Override
long /*int*/ eventWindow () {
	return paintWindow ();
}

@Override
GdkColor getBackgroundColor () {
	return getBaseColor ();
}

@Override
GdkColor getContextBackground () {
	if (OS.GTK_VERSION >= OS.VERSION(3, 16, 0)) {
		if (background != null) {
			return display.toGdkColor (background);
		} else {
			// List is a GtkTreeView, same as Table/Tree: its default
			// background color is COLOR_LIST_BACKGROUND.
			return display.COLOR_LIST_BACKGROUND;
		}
	} else {
		return super.getContextBackground ();
	}
}

/**
 * Returns the zero-relative index of the item which currently
 * has the focus in the receiver, or -1 if no item has focus.
 *
 * @return the index of the selected item
 *
 * @exception SWTException <ul>
 *    <li>ERROR_WIDGET_DISPOSED - if the receiver has been disposed</li>
 *    <li>ERROR_THREAD_INVALID_ACCESS - if not called from the thread that created the receiver</li>
 * </ul>
 */
public int getFocusIndex () {
	checkWidget();
	long /*int*/ [] path = new long /*int*/ [1];
	OS.gtk_tree_view_get_cursor (handle, path, null);
	if (path [0] == 0) return -1;
	long /*int*/ indices = OS.gtk_tree_path_get_indices (path [0]);
	int [] index = new int []{-1};
	if (indices != 0) OS.memmove (index, indices, 4);
	OS.gtk_tree_path_free (path [0]);
	return index [0];
}

@Override
GdkColor getForegroundColor () {
	return getTextColor ();
}

/**
 * Returns the item at the given, zero-relative index in the
 * receiver. Throws an exception if the index is out of range.
 *
 * @param index the index of the item to return
 * @return the item at the given index
 *
 * @exception IllegalArgumentException <ul>
 *    <li>ERROR_INVALID_RANGE - if the index is not between 0 and the number of elements in the list minus 1 (inclusive)</li>
 * </ul>
 * @exception SWTException <ul>
 *    <li>ERROR_WIDGET_DISPOSED - if the receiver has been disposed</li>
 *    <li>ERROR_THREAD_INVALID_ACCESS - if not called from the thread that created the receiver</li>
 * </ul>
 */
public String getItem (int index) {
	checkWidget();
	if (!(0 <= index && index < OS.gtk_tree_model_iter_n_children (modelHandle, 0)))  {
		error (SWT.ERROR_INVALID_RANGE);
	}
	long /*int*/ [] ptr = new long /*int*/ [1];
	long /*int*/ iter = OS.g_malloc (OS.GtkTreeIter_sizeof ());
	OS.gtk_tree_model_iter_nth_child (modelHandle, iter, 0, index);
	OS.gtk_tree_model_get (modelHandle, iter, 0, ptr, -1);
	OS.g_free (iter);
	if (ptr [0] == 0) return null;
	int length = OS.strlen (ptr [0]);
	byte[] buffer2 = new byte [length];
	OS.memmove (buffer2, ptr [0], length);
	OS.g_free (ptr [0]);
	return new String (Converter.mbcsToWcs (buffer2));
}

/**
 * Returns the number of items contained in the receiver.
 *
 * @return the number of items
 *
 * @exception SWTException <ul>
 *    <li>ERROR_WIDGET_DISPOSED - if the receiver has been disposed</li>
 *    <li>ERROR_THREAD_INVALID_ACCESS - if not called from the thread that created the receiver</li>
 * </ul>
 */
public int getItemCount () {
	checkWidget();
	return OS.gtk_tree_model_iter_n_children (modelHandle, 0);
}

/**
 * Returns the height of the area which would be used to
 * display <em>one</em> of the items in the list.
 *
 * @return the height of one item
 *
 * @exception SWTException <ul>
 *    <li>ERROR_WIDGET_DISPOSED - if the receiver has been disposed</li>
 *    <li>ERROR_THREAD_INVALID_ACCESS - if not called from the thread that created the receiver</li>
 * </ul>
 */
public int getItemHeight () {
	checkWidget();
	return DPIUtil.autoScaleDown(getItemHeightInPixels());
}

int getItemHeightInPixels () {
	checkWidget();
	int itemCount = OS.gtk_tree_model_iter_n_children (modelHandle, 0);
	long /*int*/ column = OS.gtk_tree_view_get_column (handle, 0);
	if (itemCount == 0) {
		int [] w = new int [1], h = new int [1];
		OS.gtk_tree_view_column_cell_get_size (column, null, null, null, w, h);
		int height = h [0];
		if (OS.GTK3) {
			long /*int*/ textRenderer = getTextRenderer (column);
			OS.gtk_cell_renderer_get_preferred_height_for_width (textRenderer, handle, 0, h, null);
			height += h [0];
		}
		return height;
	} else {
		long /*int*/ iter = OS.g_malloc (OS.GtkTreeIter_sizeof ());
		OS.gtk_tree_model_get_iter_first (modelHandle, iter);
		OS.gtk_tree_view_column_cell_set_cell_data (column, modelHandle, iter, false, false);
		int [] w = new int [1], h = new int [1];
		OS.gtk_tree_view_column_cell_get_size (column, null, null, null, w, h);
		OS.g_free (iter);
		return h [0];
	}
}

/**
 * Returns a (possibly empty) array of <code>String</code>s which
 * are the items in the receiver.
 * <p>
 * Note: This is not the actual structure used by the receiver
 * to maintain its list of items, so modifying the array will
 * not affect the receiver.
 * </p>
 *
 * @return the items in the receiver's list
 *
 * @exception SWTException <ul>
 *    <li>ERROR_WIDGET_DISPOSED - if the receiver has been disposed</li>
 *    <li>ERROR_THREAD_INVALID_ACCESS - if not called from the thread that created the receiver</li>
 * </ul>
 */
public String [] getItems () {
	checkWidget();
	int count = OS.gtk_tree_model_iter_n_children (modelHandle, 0);
	long /*int*/ [] ptr = new long /*int*/ [1];
	String [] result = new String [count];
	long /*int*/ iter = OS.g_malloc (OS.GtkTreeIter_sizeof ());
	for (int index=0; index<count; index++) {
		OS.gtk_tree_model_iter_nth_child (modelHandle, iter, 0, index);
		OS.gtk_tree_model_get (modelHandle, iter, 0, ptr, -1);
		if (ptr [0] != 0) {
			int length = OS.strlen (ptr [0]);
			byte[] buffer = new byte [length];
			OS.memmove (buffer, ptr [0], length);
			OS.g_free (ptr [0]);
			result [index] = new String (Converter.mbcsToWcs (buffer));
		}
	}
	OS.g_free (iter);
	return result;
}

/**
 * Returns an array of <code>String</code>s that are currently
 * selected in the receiver.  The order of the items is unspecified.
 * An empty array indicates that no items are selected.
 * <p>
 * Note: This is not the actual structure used by the receiver
 * to maintain its selection, so modifying the array will
 * not affect the receiver.
 * </p>
 * @return an array representing the selection
 *
 * @exception SWTException <ul>
 *    <li>ERROR_WIDGET_DISPOSED - if the receiver has been disposed</li>
 *    <li>ERROR_THREAD_INVALID_ACCESS - if not called from the thread that created the receiver</li>
 * </ul>
 */
public String [] getSelection () {
	checkWidget();
	int [] indices = getSelectionIndices ();
	String [] result = new String [indices.length];
	for (int i=0; i<indices.length; i++) {
		result [i] = getItem (indices [i]);
	}
	return result;
}

/**
 * Returns the number of selected items contained in the receiver.
 *
 * @return the number of selected items
 *
 * @exception SWTException <ul>
 *    <li>ERROR_WIDGET_DISPOSED - if the receiver has been disposed</li>
 *    <li>ERROR_THREAD_INVALID_ACCESS - if not called from the thread that created the receiver</li>
 * </ul>
 */
public int getSelectionCount () {
	checkWidget();
	long /*int*/ selection = OS.gtk_tree_view_get_selection (handle);
	return OS.gtk_tree_selection_count_selected_rows (selection);
}

/**
 * Returns the zero-relative index of the item which is currently
 * selected in the receiver, or -1 if no item is selected.
 *
 * @return the index of the selected item or -1
 *
 * @exception SWTException <ul>
 *    <li>ERROR_WIDGET_DISPOSED - if the receiver has been disposed</li>
 *    <li>ERROR_THREAD_INVALID_ACCESS - if not called from the thread that created the receiver</li>
 * </ul>
 */
public int getSelectionIndex () {
	checkWidget();
	long /*int*/ selection = OS.gtk_tree_view_get_selection (handle);
	long /*int*/ list = OS.gtk_tree_selection_get_selected_rows (selection, null);
	long /*int*/ originalList = list;
	if (list != 0) {
		int [] index = new int [1];
		boolean foundIndex = false;
		while (list != 0) {
			long /*int*/ data = OS.g_list_data (list);
			if (foundIndex == false) {
				long /*int*/ indices = OS.gtk_tree_path_get_indices (data);
				if (indices !=0) {
					OS.memmove (index, indices, 4);
					foundIndex = true;
				}
			}
			list = OS.g_list_next (list);
			OS.gtk_tree_path_free (data);
		}
		OS.g_list_free (originalList);
		return index [0];
	}
	return -1;
}

/**
 * Returns the zero-relative indices of the items which are currently
 * selected in the receiver.  The order of the indices is unspecified.
 * The array is empty if no items are selected.
 * <p>
 * Note: This is not the actual structure used by the receiver
 * to maintain its selection, so modifying the array will
 * not affect the receiver.
 * </p>
 * @return the array of indices of the selected items
 *
 * @exception SWTException <ul>
 *    <li>ERROR_WIDGET_DISPOSED - if the receiver has been disposed</li>
 *    <li>ERROR_THREAD_INVALID_ACCESS - if not called from the thread that created the receiver</li>
 * </ul>
 */
public int [] getSelectionIndices () {
	checkWidget();
	long /*int*/ selection = OS.gtk_tree_view_get_selection (handle);
	long /*int*/ list = OS.gtk_tree_selection_get_selected_rows (selection, null);
	long /*int*/ originalList = list;
	if (list != 0) {
		int count = OS.g_list_length (list);
		int [] treeSelection = new int [count];
		int length = 0;
		for (int i=0; i<count; i++) {
			long /*int*/ data = OS.g_list_data (list);
			long /*int*/ indices = OS.gtk_tree_path_get_indices (data);
			if (indices != 0) {
				int [] index = new int [1];
				OS.memmove (index, indices, 4);
				treeSelection [length] = index [0];
				length++;
			}
			OS.gtk_tree_path_free (data);
			list = OS.g_list_next (list);
		}
		OS.g_list_free (originalList);
		int [] result = new int [length];
		System.arraycopy (treeSelection, 0, result, 0, length);
		return result;
	}
	return new int [0];
}

long /*int*/ getTextRenderer (long /*int*/ column) {
	long /*int*/ list = OS.gtk_cell_layout_get_cells(column);
	if (list == 0) return 0;
	long /*int*/ originalList = list;
	long /*int*/ textRenderer = 0;
	while (list != 0) {
		long /*int*/ renderer = OS.g_list_data (list);
		if (OS.GTK_IS_CELL_RENDERER_TEXT (renderer)) {
			textRenderer = renderer;
			break;
		}
		list = OS.g_list_next (list);
	}
	OS.g_list_free (originalList);
	return textRenderer;
}

/**
 * Returns the zero-relative index of the item which is currently
 * at the top of the receiver. This index can change when items are
 * scrolled or new items are added or removed.
 *
 * @return the index of the top item
 *
 * @exception SWTException <ul>
 *    <li>ERROR_WIDGET_DISPOSED - if the receiver has been disposed</li>
 *    <li>ERROR_THREAD_INVALID_ACCESS - if not called from the thread that created the receiver</li>
 * </ul>
 */
public int getTopIndex () {
	checkWidget();
	/*
	 * Feature in GTK: fetch the topIndex using the topIndex global variable
	 * if setTopIndex() has been called and the widget has not been scrolled
	 * using the UI. Otherwise, fetch topIndex using GtkTreeView API.
	 */
	long /*int*/ vAdjustment;
	if (OS.GTK3) {
		vAdjustment = OS.gtk_scrollable_get_vadjustment (handle);
	} else {
		vAdjustment = OS.gtk_tree_view_get_vadjustment (handle);
	}
	currentAdjustment = OS.gtk_adjustment_get_value (vAdjustment);
	if (cachedAdjustment == currentAdjustment) {
		return topIndex;
	} else {
		long /*int*/ [] path = new long /*int*/ [1];
		OS.gtk_widget_realize (handle);
		if (!OS.gtk_tree_view_get_path_at_pos (handle, 1, 1, path, null, null, null)) return 0;
		if (path [0] == 0) return 0;
		long /*int*/ indices = OS.gtk_tree_path_get_indices (path[0]);
		int[] index = new int [1];
		if (indices != 0) OS.memmove (index, indices, 4);
		OS.gtk_tree_path_free (path [0]);
		return index [0];
	}
}

@Override
long /*int*/ gtk_changed (long /*int*/ widget) {
	sendSelectionEvent (SWT.Selection);
	return 0;
}

@Override
long /*int*/ gtk_event_after (long /*int*/ widget, long /*int*/ gdkEvent) {
	switch (OS.GDK_EVENT_TYPE (gdkEvent)) {
		case OS.GDK_EXPOSE: {
			/*
			* Bug in GTK. SWT connects the expose-event 'after' the default
			* handler of the signal. If the tree has no children, then GTK
			* sends expose signal only 'before' the default signal handler.
			* The fix is to detect this case in 'event_after' and send the
			* expose event.
			*/
			if (OS.gtk_tree_model_iter_n_children (modelHandle, 0) == 0) {
				gtk_expose_event (widget, gdkEvent);
			}
			break;
		}
	}
	return super.gtk_event_after (widget, gdkEvent);
}

@Override
long /*int*/ gtk_button_press_event (long /*int*/ widget, long /*int*/ event) {
	long /*int*/ result = super.gtk_button_press_event (widget, event);
	if (result != 0) return result;
	/*
	* Feature in GTK.  In a multi-select list view, when multiple items are already
	* selected, the selection state of the item is toggled and the previous selection
	* is cleared. This is not the desired behaviour when bringing up a popup menu.
	* Also, when an item is reselected with the right button, the tree view issues
	* an unwanted selection event. The workaround is to detect that case and not
	* run the default handler when the item is already part of the current selection.
	*/
	GdkEventButton gdkEvent = new GdkEventButton ();
	OS.memmove (gdkEvent, event, GdkEventButton.sizeof);
	int button = gdkEvent.button;
	if (button == 3 && gdkEvent.type == OS.GDK_BUTTON_PRESS) {
		long /*int*/ [] path = new long /*int*/ [1];
		if (OS.gtk_tree_view_get_path_at_pos (handle, (int)gdkEvent.x, (int)gdkEvent.y, path, null, null, null)) {
			if (path [0] != 0) {
				long /*int*/ selection = OS.gtk_tree_view_get_selection (handle);
				if (OS.gtk_tree_selection_path_is_selected (selection, path [0])) result = 1;
				OS.gtk_tree_path_free (path [0]);
			}
		}
	}

	/*
	* Feature in GTK.  When the user clicks in a single selection GtkTreeView
	* and there are no selected items, the first item is selected automatically
	* before the click is processed, causing two selection events.  The is fix
	* is the set the cursor item to be same as the clicked item to stop the
	* widget from automatically selecting the first item.
	*/
	if ((style & SWT.SINGLE) != 0 && getSelectionCount () == 0) {
		long /*int*/ [] path = new long /*int*/ [1];
		if (OS.gtk_tree_view_get_path_at_pos (handle, (int)gdkEvent.x, (int)gdkEvent.y, path, null, null, null)) {
			if (path [0] != 0) {
				long /*int*/ selection = OS.gtk_tree_view_get_selection (handle);
				OS.g_signal_handlers_block_matched (selection, OS.G_SIGNAL_MATCH_DATA, 0, 0, 0, 0, CHANGED);
				OS.gtk_tree_view_set_cursor (handle, path [0], 0, false);
				OS.g_signal_handlers_unblock_matched (selection, OS.G_SIGNAL_MATCH_DATA, 0, 0, 0, 0, CHANGED);
				OS.gtk_tree_path_free (path [0]);
			}
		}
	}

	//If Mouse double-click pressed, manually send a DefaultSelection.  See Bug 312568.
	if (gdkEvent.type == OS.GDK_2BUTTON_PRESS) {
		sendTreeDefaultSelection ();
	}

	return result;
}


@Override
long /*int*/ gtk_key_press_event (long /*int*/ widget, long /*int*/ event) {
	GdkEventKey keyEvent = new GdkEventKey ();
	OS.memmove (keyEvent, event, GdkEventKey.sizeof);
	int key = keyEvent.keyval;
	keyPressDefaultSelectionHandler (event, key);
	return super.gtk_key_press_event (widget, event);
}

/**
 * Used to emulate DefaultSelection event. See Bug 312568.
 * @param event the gtk key press event that was fired.
 */
void keyPressDefaultSelectionHandler (long /*int*/ event, int key) {
	int keymask = gdk_event_get_state (event);
	switch (key) {
		case OS.GDK_Return:
			// Send DefaultSelectionEvent when: Enter, Shift+Enter, Ctrl+Enter, Alt+Enter are pressed.
			// Not when (Meta|Super|Hyper)+Enter, reason is stateMask is not provided on Gtk. Does not trigger on Win32 either.
			if ((keymask & (OS.GDK_SUPER_MASK | OS.GDK_META_MASK | OS.GDK_HYPER_MASK)) == 0) {
				sendTreeDefaultSelection ();
			}
			break;
	}
}

//Used to emulate DefaultSelection event. See Bug 312568.
//Feature in GTK. 'row-activation' event comes before DoubleClick event.
//This is causing the editor not to get focus after doubleclick.
//The solution is to manually send the DefaultSelection event after a doubleclick,
//and to emulate it for Space/Return.
void sendTreeDefaultSelection() {

	//Note, similar DefaultSelectionHandling in SWT List/Table/Tree
	Event event = new Event ();
	event.index = this.getFocusIndex ();

	if (event.index >= 0)
		event.text = this.getItem (event.index);
	sendSelectionEvent (SWT.DefaultSelection, event, false);
}

@Override
void hookEvents () {
	super.hookEvents();
	long /*int*/ selection = OS.gtk_tree_view_get_selection(handle);
	OS.g_signal_connect_closure (selection, OS.changed, display.getClosure (CHANGED), false);
	OS.g_signal_connect_closure (handle, OS.row_activated, display.getClosure (ROW_ACTIVATED), false);
}

/**
 * Gets the index of an item.
 * <p>
 * The list is searched starting at 0 until an
 * item is found that is equal to the search item.
 * If no item is found, -1 is returned.  Indexing
 * is zero based.
 *
 * @param string the search item
 * @return the index of the item
 *
 * @exception IllegalArgumentException <ul>
 *    <li>ERROR_NULL_ARGUMENT - if the string is null</li>
 * </ul>
 * @exception SWTException <ul>
 *    <li>ERROR_WIDGET_DISPOSED - if the receiver has been disposed</li>
 *    <li>ERROR_THREAD_INVALID_ACCESS - if not called from the thread that created the receiver</li>
 * </ul>
 */
public int indexOf (String string) {
	checkWidget();
	return indexOf (string, 0);
}

/**
 * Searches the receiver's list starting at the given,
 * zero-relative index until an item is found that is equal
 * to the argument, and returns the index of that item. If
 * no item is found or the starting index is out of range,
 * returns -1.
 *
 * @param string the search item
 * @param start the zero-relative index at which to start the search
 * @return the index of the item
 *
 * @exception IllegalArgumentException <ul>
 *    <li>ERROR_NULL_ARGUMENT - if the string is null</li>
 * </ul>
 * @exception SWTException <ul>
 *    <li>ERROR_WIDGET_DISPOSED - if the receiver has been disposed</li>
 *    <li>ERROR_THREAD_INVALID_ACCESS - if not called from the thread that created the receiver</li>
 * </ul>
 */
public int indexOf (String string, int start) {
	checkWidget();
	if (string == null) error (SWT.ERROR_NULL_ARGUMENT);
	String [] items = getItems ();
	for (int i=start; i<items.length; i++) {
		if (items [i].equals (string)) return i;
	}
	return -1;
}

/**
 * Returns <code>true</code> if the item is selected,
 * and <code>false</code> otherwise.  Indices out of
 * range are ignored.
 *
 * @param index the index of the item
 * @return the selection state of the item at the index
 *
 * @exception SWTException <ul>
 *    <li>ERROR_WIDGET_DISPOSED - if the receiver has been disposed</li>
 *    <li>ERROR_THREAD_INVALID_ACCESS - if not called from the thread that created the receiver</li>
 * </ul>
 */
public boolean isSelected (int index) {
	checkWidget();
	long /*int*/ selection = OS.gtk_tree_view_get_selection (handle);
	byte [] buffer = Converter.wcsToMbcs (Integer.toString (index), true);
	long /*int*/ path = OS.gtk_tree_path_new_from_string (buffer);
	boolean answer = OS.gtk_tree_selection_path_is_selected (selection, path);
	OS.gtk_tree_path_free (path);
	return answer;
}

@Override
long /*int*/ paintWindow () {
	OS.gtk_widget_realize (handle);
	return OS.gtk_tree_view_get_bin_window (handle);
}

@Override
void register () {
	super.register ();
	display.addWidget (OS.gtk_tree_view_get_selection (handle), this);
}

@Override
void releaseWidget () {
	super.releaseWidget ();
	if (modelHandle != 0) OS.g_object_unref (modelHandle);
	modelHandle = 0;
}

/**
 * Removes the item from the receiver at the given
 * zero-relative index.
 *
 * @param index the index for the item
 *
 * @exception IllegalArgumentException <ul>
 *    <li>ERROR_INVALID_RANGE - if the index is not between 0 and the number of elements in the list minus 1 (inclusive)</li>
 * </ul>
 * @exception SWTException <ul>
 *    <li>ERROR_WIDGET_DISPOSED - if the receiver has been disposed</li>
 *    <li>ERROR_THREAD_INVALID_ACCESS - if not called from the thread that created the receiver</li>
 * </ul>
 */
public void remove (int index) {
	checkWidget();
	if (!(0 <= index && index < OS.gtk_tree_model_iter_n_children (modelHandle, 0)))  {
		error (SWT.ERROR_INVALID_RANGE);
	}
	long /*int*/ iter = OS.g_malloc (OS.GtkTreeIter_sizeof ());
	OS.gtk_tree_model_iter_nth_child (modelHandle, iter, 0, index);
	long /*int*/ selection = OS.gtk_tree_view_get_selection (handle);
	OS.g_signal_handlers_block_matched (selection, OS.G_SIGNAL_MATCH_DATA, 0, 0, 0, 0, CHANGED);
	OS.gtk_list_store_remove (modelHandle, iter);
	OS.g_signal_handlers_unblock_matched (selection, OS.G_SIGNAL_MATCH_DATA, 0, 0, 0, 0, CHANGED);
	OS.g_free (iter);
}

/**
 * Removes the items from the receiver which are
 * between the given zero-relative start and end
 * indices (inclusive).
 *
 * @param start the start of the range
 * @param end the end of the range
 *
 * @exception IllegalArgumentException <ul>
 *    <li>ERROR_INVALID_RANGE - if either the start or end are not between 0 and the number of elements in the list minus 1 (inclusive)</li>
 * </ul>
 * @exception SWTException <ul>
 *    <li>ERROR_WIDGET_DISPOSED - if the receiver has been disposed</li>
 *    <li>ERROR_THREAD_INVALID_ACCESS - if not called from the thread that created the receiver</li>
 * </ul>
 */
public void remove (int start, int end) {
	checkWidget();
	if (start > end) return;
	int count =  OS.gtk_tree_model_iter_n_children (modelHandle, 0);
	if (!(0 <= start && start <= end && end < count)) {
		error (SWT.ERROR_INVALID_RANGE);
	}
	long /*int*/ iter = OS.g_malloc (OS.GtkTreeIter_sizeof ());
	long /*int*/ selection = OS.gtk_tree_view_get_selection (handle);
	OS.g_signal_handlers_block_matched (selection, OS.G_SIGNAL_MATCH_DATA, 0, 0, 0, 0, CHANGED);
	for (int index=end; index>=start; index--) {
		OS.gtk_tree_model_iter_nth_child (modelHandle, iter, 0, index);
		OS.gtk_list_store_remove (modelHandle, iter);
	}
	OS.g_signal_handlers_unblock_matched (selection, OS.G_SIGNAL_MATCH_DATA, 0, 0, 0, 0, CHANGED);
	OS.g_free (iter);
}

/**
 * Searches the receiver's list starting at the first item
 * until an item is found that is equal to the argument,
 * and removes that item from the list.
 *
 * @param string the item to remove
 *
 * @exception IllegalArgumentException <ul>
 *    <li>ERROR_NULL_ARGUMENT - if the string is null</li>
 *    <li>ERROR_INVALID_ARGUMENT - if the string is not found in the list</li>
 * </ul>
 * @exception SWTException <ul>
 *    <li>ERROR_WIDGET_DISPOSED - if the receiver has been disposed</li>
 *    <li>ERROR_THREAD_INVALID_ACCESS - if not called from the thread that created the receiver</li>
 * </ul>
 */
public void remove (String string) {
	checkWidget();
	if (string == null) error (SWT.ERROR_NULL_ARGUMENT);
	int index = indexOf (string, 0);
	if (index == -1) error (SWT.ERROR_INVALID_ARGUMENT);
	remove (index);
}

/**
 * Removes the items from the receiver at the given
 * zero-relative indices.
 *
 * @param indices the array of indices of the items
 *
 * @exception IllegalArgumentException <ul>
 *    <li>ERROR_INVALID_RANGE - if the index is not between 0 and the number of elements in the list minus 1 (inclusive)</li>
 *    <li>ERROR_NULL_ARGUMENT - if the indices array is null</li>
 * </ul>
 * @exception SWTException <ul>
 *    <li>ERROR_WIDGET_DISPOSED - if the receiver has been disposed</li>
 *    <li>ERROR_THREAD_INVALID_ACCESS - if not called from the thread that created the receiver</li>
 * </ul>
 */
public void remove (int [] indices) {
	checkWidget();
	if (indices == null) error (SWT.ERROR_NULL_ARGUMENT);
	if (indices.length == 0) return;
	int [] newIndices = new int [indices.length];
	System.arraycopy (indices, 0, newIndices, 0, indices.length);
	sort (newIndices);
	int start = newIndices [newIndices.length - 1], end = newIndices [0];
	int count = getItemCount();
	if (!(0 <= start && start <= end && end < count)) {
		error (SWT.ERROR_INVALID_RANGE);
	}
	long /*int*/ iter = OS.g_malloc (OS.GtkTreeIter_sizeof ());
	long /*int*/ selection = OS.gtk_tree_view_get_selection (handle);
	OS.g_signal_handlers_block_matched (selection, OS.G_SIGNAL_MATCH_DATA, 0, 0, 0, 0, CHANGED);
	int last = -1;
	for (int i=0; i<newIndices.length; i++) {
		int index = newIndices [i];
		if (index != last) {
			OS.gtk_tree_model_iter_nth_child (modelHandle, iter, 0, index);
			OS.gtk_list_store_remove (modelHandle, iter);
			last = index;
		}
	}
	OS.g_signal_handlers_unblock_matched (selection, OS.G_SIGNAL_MATCH_DATA, 0, 0, 0, 0, CHANGED);
	OS.g_free (iter);
}

/**
 * Removes all of the items from the receiver.
 * <p>
 * @exception SWTException <ul>
 *    <li>ERROR_WIDGET_DISPOSED - if the receiver has been disposed</li>
 *    <li>ERROR_THREAD_INVALID_ACCESS - if not called from the thread that created the receiver</li>
 * </ul>
 */
public void removeAll () {
	checkWidget();
	long /*int*/ selection = OS.gtk_tree_view_get_selection (handle);
	OS.g_signal_handlers_block_matched (selection, OS.G_SIGNAL_MATCH_DATA, 0, 0, 0, 0, CHANGED);
	OS.gtk_list_store_clear (modelHandle);
	OS.g_signal_handlers_unblock_matched (selection, OS.G_SIGNAL_MATCH_DATA, 0, 0, 0, 0, CHANGED);
}

/**
 * Removes the listener from the collection of listeners who will
 * be notified when the user changes the receiver's selection.
 *
 * @param listener the listener which should no longer be notified
 *
 * @exception IllegalArgumentException <ul>
 *    <li>ERROR_NULL_ARGUMENT - if the listener is null</li>
 * </ul>
 * @exception SWTException <ul>
 *    <li>ERROR_WIDGET_DISPOSED - if the receiver has been disposed</li>
 *    <li>ERROR_THREAD_INVALID_ACCESS - if not called from the thread that created the receiver</li>
 * </ul>
 *
 * @see SelectionListener
 * @see #addSelectionListener
 */
public void removeSelectionListener(SelectionListener listener) {
	checkWidget();
	if (listener == null) error (SWT.ERROR_NULL_ARGUMENT);
	if (eventTable == null) return;
	eventTable.unhook (SWT.Selection, listener);
	eventTable.unhook (SWT.DefaultSelection,listener);
}

/**
 * Selects the item at the given zero-relative index in the receiver's
 * list.  If the item at the index was already selected, it remains
 * selected. Indices that are out of range are ignored.
 *
 * @param index the index of the item to select
 *
 * @exception SWTException <ul>
 *    <li>ERROR_WIDGET_DISPOSED - if the receiver has been disposed</li>
 *    <li>ERROR_THREAD_INVALID_ACCESS - if not called from the thread that created the receiver</li>
 * </ul>
 */
public void select (int index) {
	checkWidget();
	if (!(0 <= index && index < OS.gtk_tree_model_iter_n_children (modelHandle, 0)))  return;
	long /*int*/ iter = OS.g_malloc (OS.GtkTreeIter_sizeof ());
	long /*int*/ selection = OS.gtk_tree_view_get_selection (handle);
	OS.g_signal_handlers_block_matched (selection, OS.G_SIGNAL_MATCH_DATA, 0, 0, 0, 0, CHANGED);
	OS.gtk_tree_model_iter_nth_child (modelHandle, iter, 0, index);
	OS.gtk_tree_selection_select_iter (selection, iter);
	if ((style & SWT.SINGLE) != 0) {
		long /*int*/ path = OS.gtk_tree_model_get_path (modelHandle, iter);
		OS.gtk_tree_view_set_cursor (handle, path, 0, false);
		OS.gtk_tree_path_free (path);
	}
	OS.g_signal_handlers_unblock_matched (selection, OS.G_SIGNAL_MATCH_DATA, 0, 0, 0, 0, CHANGED);
	OS.g_free (iter);
}

/**
 * Selects the items in the range specified by the given zero-relative
 * indices in the receiver. The range of indices is inclusive.
 * The current selection is not cleared before the new items are selected.
 * <p>
 * If an item in the given range is not selected, it is selected.
 * If an item in the given range was already selected, it remains selected.
 * Indices that are out of range are ignored and no items will be selected
 * if start is greater than end.
 * If the receiver is single-select and there is more than one item in the
 * given range, then all indices are ignored.
 *
 * @param start the start of the range
 * @param end the end of the range
 *
 * @exception SWTException <ul>
 *    <li>ERROR_WIDGET_DISPOSED - if the receiver has been disposed</li>
 *    <li>ERROR_THREAD_INVALID_ACCESS - if not called from the thread that created the receiver</li>
 * </ul>
 *
 * @see List#setSelection(int,int)
 */
public void select (int start, int end) {
	checkWidget ();
	if (end < 0 || start > end || ((style & SWT.SINGLE) != 0 && start != end)) return;
	int count = OS.gtk_tree_model_iter_n_children (modelHandle, 0);
	if (count == 0 || start >= count) return;
	start = Math.max (0, start);
	end = Math.min (end, count - 1);
	long /*int*/ iter = OS.g_malloc (OS.GtkTreeIter_sizeof ());
	long /*int*/ selection = OS.gtk_tree_view_get_selection (handle);
	OS.g_signal_handlers_block_matched (selection, OS.G_SIGNAL_MATCH_DATA, 0, 0, 0, 0, CHANGED);
	for (int index=start; index<=end; index++) {
		OS.gtk_tree_model_iter_nth_child (modelHandle, iter, 0, index);
		OS.gtk_tree_selection_select_iter (selection, iter);
		if ((style & SWT.SINGLE) != 0) {
			long /*int*/ path = OS.gtk_tree_model_get_path (modelHandle, iter);
			OS.gtk_tree_view_set_cursor (handle, path, 0, false);
			OS.gtk_tree_path_free (path);
		}
	}
	OS.g_signal_handlers_unblock_matched (selection, OS.G_SIGNAL_MATCH_DATA, 0, 0, 0, 0, CHANGED);
	OS.g_free (iter);
}

/**
 * Selects the items at the given zero-relative indices in the receiver.
 * The current selection is not cleared before the new items are selected.
 * <p>
 * If the item at a given index is not selected, it is selected.
 * If the item at a given index was already selected, it remains selected.
 * Indices that are out of range and duplicate indices are ignored.
 * If the receiver is single-select and multiple indices are specified,
 * then all indices are ignored.
 *
 * @param indices the array of indices for the items to select
 *
 * @exception IllegalArgumentException <ul>
 *    <li>ERROR_NULL_ARGUMENT - if the array of indices is null</li>
 * </ul>
 * @exception SWTException <ul>
 *    <li>ERROR_WIDGET_DISPOSED - if the receiver has been disposed</li>
 *    <li>ERROR_THREAD_INVALID_ACCESS - if not called from the thread that created the receiver</li>
 * </ul>
 *
 * @see List#setSelection(int[])
 */
public void select (int [] indices) {
	checkWidget ();
	if (indices == null) error (SWT.ERROR_NULL_ARGUMENT);
	int length = indices.length;
	if (length == 0 || ((style & SWT.SINGLE) != 0 && length > 1)) return;
	long /*int*/ iter = OS.g_malloc (OS.GtkTreeIter_sizeof ());
	int count = OS.gtk_tree_model_iter_n_children (modelHandle, 0);
	long /*int*/ selection = OS.gtk_tree_view_get_selection (handle);
	OS.g_signal_handlers_block_matched (selection, OS.G_SIGNAL_MATCH_DATA, 0, 0, 0, 0, CHANGED);
	for (int i=0; i<length; i++) {
		int index = indices [i];
		if (!(0 <= index && index < count)) continue;
		OS.gtk_tree_model_iter_nth_child (modelHandle, iter, 0, index);
		OS.gtk_tree_selection_select_iter (selection, iter);
		if ((style & SWT.SINGLE) != 0) {
			long /*int*/ path = OS.gtk_tree_model_get_path (modelHandle, iter);
			OS.gtk_tree_view_set_cursor (handle, path, 0, false);
			OS.gtk_tree_path_free (path);
		}
	}
	OS.g_signal_handlers_unblock_matched (selection, OS.G_SIGNAL_MATCH_DATA, 0, 0, 0, 0, CHANGED);
	OS.g_free (iter);
}

/**
 * Selects all of the items in the receiver.
 * <p>
 * If the receiver is single-select, do nothing.
 *
 * @exception SWTException <ul>
 *    <li>ERROR_WIDGET_DISPOSED - if the receiver has been disposed</li>
 *    <li>ERROR_THREAD_INVALID_ACCESS - if not called from the thread that created the receiver</li>
 * </ul>
 */
public void selectAll () {
	checkWidget();
	if ((style & SWT.SINGLE) != 0) return;
	long /*int*/ selection = OS.gtk_tree_view_get_selection (handle);
	OS.g_signal_handlers_block_matched (selection, OS.G_SIGNAL_MATCH_DATA, 0, 0, 0, 0, CHANGED);
	OS.gtk_tree_selection_select_all (selection);
	OS.g_signal_handlers_unblock_matched (selection, OS.G_SIGNAL_MATCH_DATA, 0, 0, 0, 0, CHANGED);
}

void selectFocusIndex (int index) {
	/*
	* Note that this method both selects and sets the focus to the
	* specified index, so any previous selection in the list will be lost.
	* gtk does not provide a way to just set focus to a specified list item.
	*/
	int count = OS.gtk_tree_model_iter_n_children (modelHandle, 0);
	if (!(0 <= index && index < count))  return;
	long /*int*/ iter = OS.g_malloc (OS.GtkTreeIter_sizeof ());
	OS.gtk_tree_model_iter_nth_child (modelHandle, iter, 0, index);
	long /*int*/ path = OS.gtk_tree_model_get_path (modelHandle, iter);
	long /*int*/ selection = OS.gtk_tree_view_get_selection (handle);
	OS.g_signal_handlers_block_matched (selection, OS.G_SIGNAL_MATCH_DATA, 0, 0, 0, 0, CHANGED);
	OS.gtk_tree_view_set_cursor (handle, path, 0, false);
	OS.g_signal_handlers_unblock_matched (selection, OS.G_SIGNAL_MATCH_DATA, 0, 0, 0, 0, CHANGED);
	OS.gtk_tree_path_free (path);
	OS.g_free (iter);
}

@Override
void setBackgroundColor (GdkColor color) {
	super.setBackgroundColor (color);
	if (!OS.GTK3) {
		OS.gtk_widget_modify_base (handle, 0, color);
	}
}

@Override
void setBackgroundColor (long /*int*/ context, long /*int*/ handle, GdkRGBA rgba) {
	/* Setting the background color overrides the selected background color.
	 * To prevent this, we need to re-set the default. This can be done with CSS
	 * on GTK3.16+, or by using GtkStateFlags as an argument to
	 * gtk_widget_override_background_color() on versions of GTK3 less than 3.16.
	 */
	if (rgba == null) {
		GdkColor temp = getDisplay().COLOR_LIST_BACKGROUND;
		background = display.toGdkRGBA (temp);
	} else {
		background = rgba;
	}
	GdkColor defaultColor = getDisplay().COLOR_LIST_SELECTION;
	GdkRGBA selectedBackground = display.toGdkRGBA (defaultColor);
	if (OS.GTK_VERSION >= OS.VERSION(3, 16, 0)) {
		String name = OS.GTK_VERSION >= OS.VERSION(3, 20, 0) ? "treeview" : "GtkTreeView";
		String css = name + " {background-color: " + display.gtk_rgba_to_css_string(background) + ";}\n"
                + name + ":selected {background-color: " + display.gtk_rgba_to_css_string(selectedBackground) + ";}";

		// Cache background color
		cssBackground = css;

		// Apply background color and any foreground color
		String finalCss = display.gtk_css_create_css_color_string (cssBackground, cssForeground, SWT.BACKGROUND);
		gtk_css_provider_load_from_css(context, finalCss);
	} else {
		super.setBackgroundColor(context, handle, rgba);
		OS.gtk_widget_override_background_color(handle, OS.GTK_STATE_FLAG_SELECTED, selectedBackground);
	}
}

@Override
int setBounds (int x, int y, int width, int height, boolean move, boolean resize) {
	int result = super.setBounds (x, y, width, height, move, resize);
	/*
	* Bug on GTK.  The tree view sometimes does not get a paint
	* event or resizes to a one pixel square when resized in a new
	* shell that is not visible after any event loop has been run.  The
	* problem is intermittent. It doesn't seem to happen the first time
	* a new shell is created. The fix is to ensure the tree view is realized
	* after it has been resized.
	*/
	OS.gtk_widget_realize (handle);
	return result;
}

/**
 * Sets the text of the item in the receiver's list at the given
 * zero-relative index to the string argument.
 *
 * @param index the index for the item
 * @param string the new text for the item
 *
 * @exception IllegalArgumentException <ul>
 *    <li>ERROR_INVALID_RANGE - if the index is not between 0 and the number of elements in the list minus 1 (inclusive)</li>
 *    <li>ERROR_NULL_ARGUMENT - if the string is null</li>
 * </ul>
 * @exception SWTException <ul>
 *    <li>ERROR_WIDGET_DISPOSED - if the receiver has been disposed</li>
 *    <li>ERROR_THREAD_INVALID_ACCESS - if not called from the thread that created the receiver</li>
 * </ul>
 */
public void setItem (int index, String string) {
	checkWidget();
	if (string == null) error (SWT.ERROR_NULL_ARGUMENT);
	if (!(0 <= index && index < OS.gtk_tree_model_iter_n_children (modelHandle, 0)))  {
		error (SWT.ERROR_INVALID_RANGE);
	}
	long /*int*/ iter = OS.g_malloc (OS.GtkTreeIter_sizeof ());
	OS.gtk_tree_model_iter_nth_child (modelHandle, iter, 0, index);
	byte [] buffer = Converter.wcsToMbcs (string, true);
	OS.gtk_list_store_set (modelHandle, iter, TEXT_COLUMN, buffer, -1);
	OS.g_free (iter);
}

/**
 * Sets the receiver's items to be the given array of items.
 *
 * @param items the array of items
 *
 * @exception IllegalArgumentException <ul>
 *    <li>ERROR_NULL_ARGUMENT - if the items array is null</li>
 *    <li>ERROR_INVALID_ARGUMENT - if an item in the items array is null</li>
 * </ul>
 * @exception SWTException <ul>
 *    <li>ERROR_WIDGET_DISPOSED - if the receiver has been disposed</li>
 *    <li>ERROR_THREAD_INVALID_ACCESS - if not called from the thread that created the receiver</li>
 * </ul>
 */
public void setItems (String... items) {
	checkWidget();
	if (items == null) error (SWT.ERROR_NULL_ARGUMENT);
	for (int i=0; i<items.length; i++) {
		if (items [i] == null) error (SWT.ERROR_INVALID_ARGUMENT);
	}
	long /*int*/ selection = OS.gtk_tree_view_get_selection (handle);
	OS.g_signal_handlers_block_matched (selection, OS.G_SIGNAL_MATCH_DATA, 0, 0, 0, 0, CHANGED);
	OS.gtk_list_store_clear (modelHandle);
	OS.g_signal_handlers_unblock_matched (selection, OS.G_SIGNAL_MATCH_DATA, 0, 0, 0, 0, CHANGED);
	long /*int*/ iter = OS.g_malloc (OS.GtkTreeIter_sizeof ());
	if (iter == 0) error (SWT.ERROR_ITEM_NOT_ADDED);
	for (int i=0; i<items.length; i++) {
		String string = items [i];
		byte [] buffer = Converter.wcsToMbcs (string, true);
		OS.gtk_list_store_append (modelHandle, iter);
		OS.gtk_list_store_set (modelHandle, iter, TEXT_COLUMN, buffer, -1);
	}
	OS.g_free (iter);
}

@Override
void setForegroundColor (GdkColor color) {
	if (OS.GTK_VERSION >= OS.VERSION (3, 16, 0)) {
		GdkRGBA rgba = null;
		if (color != null) {
			rgba = display.toGdkRGBA (color);
		}
		setForegroundColor (handle, rgba);
	} else {
		setForegroundColor (handle, color, false);
	}
}

/**
 * Selects the item at the given zero-relative index in the receiver.
 * If the item at the index was already selected, it remains selected.
 * The current selection is first cleared, then the new item is selected,
 * and if necessary the receiver is scrolled to make the new selection visible.
 * Indices that are out of range are ignored.
 *
 * @param index the index of the item to select
 *
 * @exception SWTException <ul>
 *    <li>ERROR_WIDGET_DISPOSED - if the receiver has been disposed</li>
 *    <li>ERROR_THREAD_INVALID_ACCESS - if not called from the thread that created the receiver</li>
 * </ul>
 * @see List#deselectAll()
 * @see List#select(int)
 */
public void setSelection (int index) {
	checkWidget ();
	deselectAll ();
	selectFocusIndex (index);
	showSelection ();
}

/**
 * Selects the items in the range specified by the given zero-relative
 * indices in the receiver. The range of indices is inclusive.
 * The current selection is cleared before the new items are selected,
 * and if necessary the receiver is scrolled to make the new selection visible.
 * <p>
 * Indices that are out of range are ignored and no items will be selected
 * if start is greater than end.
 * If the receiver is single-select and there is more than one item in the
 * given range, then all indices are ignored.
 *
 * @param start the start index of the items to select
 * @param end the end index of the items to select
 *
 * @exception SWTException <ul>
 *    <li>ERROR_WIDGET_DISPOSED - if the receiver has been disposed</li>
 *    <li>ERROR_THREAD_INVALID_ACCESS - if not called from the thread that created the receiver</li>
 * </ul>
 *
 * @see List#deselectAll()
 * @see List#select(int,int)
 */
public void setSelection (int start, int end) {
	checkWidget ();
	deselectAll ();
	if (end < 0 || start > end || ((style & SWT.SINGLE) != 0 && start != end)) return;
	int count = OS.gtk_tree_model_iter_n_children (modelHandle, 0);
	if (count == 0 || start >= count) return;
	start = Math.max (0, start);
	end = Math.min (end, count - 1);
	selectFocusIndex (start);
	if ((style & SWT.MULTI) != 0) {
		select (start, end);
	}
	showSelection ();
}

/**
 * Selects the items at the given zero-relative indices in the receiver.
 * The current selection is cleared before the new items are selected,
 * and if necessary the receiver is scrolled to make the new selection visible.
 * <p>
 * Indices that are out of range and duplicate indices are ignored.
 * If the receiver is single-select and multiple indices are specified,
 * then all indices are ignored.
 *
 * @param indices the indices of the items to select
 *
 * @exception IllegalArgumentException <ul>
 *    <li>ERROR_NULL_ARGUMENT - if the array of indices is null</li>
 * </ul>
 * @exception SWTException <ul>
 *    <li>ERROR_WIDGET_DISPOSED - if the receiver has been disposed</li>
 *    <li>ERROR_THREAD_INVALID_ACCESS - if not called from the thread that created the receiver</li>
 * </ul>
 *
 * @see List#deselectAll()
 * @see List#select(int[])
 */
public void setSelection(int[] indices) {
	checkWidget ();
	if (indices == null) error (SWT.ERROR_NULL_ARGUMENT);
	deselectAll ();
	int length = indices.length;
	if (length == 0 || ((style & SWT.SINGLE) != 0 && length > 1)) return;
	selectFocusIndex (indices [0]);
	if ((style & SWT.MULTI) != 0) {
		select (indices);
	}
	showSelection ();
}

/**
 * Sets the receiver's selection to be the given array of items.
 * The current selection is cleared before the new items are selected,
 * and if necessary the receiver is scrolled to make the new selection visible.
 * <p>
 * Items that are not in the receiver are ignored.
 * If the receiver is single-select and multiple items are specified,
 * then all items are ignored.
 *
 * @param items the array of items
 *
 * @exception IllegalArgumentException <ul>
 *    <li>ERROR_NULL_ARGUMENT - if the array of items is null</li>
 * </ul>
 * @exception SWTException <ul>
 *    <li>ERROR_WIDGET_DISPOSED - if the receiver has been disposed</li>
 *    <li>ERROR_THREAD_INVALID_ACCESS - if not called from the thread that created the receiver</li>
 * </ul>
 *
 * @see List#deselectAll()
 * @see List#select(int[])
 * @see List#setSelection(int[])
 */
public void setSelection (String [] items) {
	checkWidget ();
	if (items == null) error (SWT.ERROR_NULL_ARGUMENT);
	deselectAll ();
	int length = items.length;
	if (length == 0 || ((style & SWT.SINGLE) != 0 && length > 1)) return;
	boolean first = true;
	for (int i = 0; i < length; i++) {
		int index = 0;
		String string = items [i];
		if (string != null) {
			while ((index = indexOf (string, index)) != -1) {
				if ((style & SWT.MULTI) != 0) {
					if (first) {
						first = false;
						selectFocusIndex (index);
					} else {
						select (index);
					}
				} else {
					selectFocusIndex (index);
					break;
				}
				index++;
			}
		}
	}
	showSelection ();
}

/**
 * Sets the zero-relative index of the item which is currently
 * at the top of the receiver. This index can change when items
 * are scrolled or new items are added and removed.
 *
 * @param index the index of the top item
 *
 * @exception SWTException <ul>
 *    <li>ERROR_WIDGET_DISPOSED - if the receiver has been disposed</li>
 *    <li>ERROR_THREAD_INVALID_ACCESS - if not called from the thread that created the receiver</li>
 * </ul>
 */
public void setTopIndex (int index) {
	checkWidget();
	/*
	 * Feature in GTK: cache the GtkAdjustment value for future use in
	 * getTopIndex(). Set topIndex to index.
	 *
	 * Use gtk_tree_view_get_view_get_vadjustment for GTK2, GtkScrollable
	 * doesn't exist on GTK2.
	 */
	long /*int*/ vAdjustment;
	if (OS.GTK3) {
		vAdjustment = OS.gtk_scrollable_get_vadjustment (handle);
	} else {
		vAdjustment = OS.gtk_tree_view_get_vadjustment (handle);
	}
	cachedAdjustment = OS.gtk_adjustment_get_value (vAdjustment);
	topIndex = index;
	/*
	 * Scroll to the cell, now that the topIndex variable has been set.
	 */
	if (!(0 <= index && index < OS.gtk_tree_model_iter_n_children (modelHandle, 0))) return;
	long /*int*/ iter = OS.g_malloc (OS.GtkTreeIter_sizeof ());
	OS.gtk_tree_model_iter_nth_child (modelHandle, iter, 0, index);
	long /*int*/ path = OS.gtk_tree_model_get_path (modelHandle, iter);
	OS.gtk_tree_view_scroll_to_cell (handle, path, 0, true, 0, 0);
	OS.gtk_tree_path_free (path);
	OS.g_free (iter);
}

/**
 * Shows the selection.  If the selection is already showing in the receiver,
 * this method simply returns.  Otherwise, the items are scrolled until
 * the selection is visible.
 *
 * @exception SWTException <ul>
 *    <li>ERROR_WIDGET_DISPOSED - if the receiver has been disposed</li>
 *    <li>ERROR_THREAD_INVALID_ACCESS - if not called from the thread that created the receiver</li>
 * </ul>
 */
public void showSelection () {
	checkWidget();
	int index = getSelectionIndex ();
	if (index == -1) return;
	long /*int*/ iter = OS.g_malloc (OS.GtkTreeIter_sizeof ());
	OS.gtk_tree_model_iter_nth_child (modelHandle, iter, 0, index);
	long /*int*/ path = OS.gtk_tree_model_get_path (modelHandle, iter);

	OS.gtk_widget_realize (handle);
	OS.gtk_tree_view_scroll_to_cell (handle, path, 0, false, 0, 0);

	/*
	 * Reverting the code for bug 459072. With gtk_tree_view_scroll_to_cell, there are some pending gtk events.
	 * the scroll does not happen immediately. We still need to perform gtk_tree_view_scroll_to_point to complete
	 * all the pending events and update top index.
	 */
	GdkRectangle visibleRect = new GdkRectangle ();
	OS.gtk_tree_view_get_visible_rect (handle, visibleRect);
	GdkRectangle cellRect = new GdkRectangle ();
	OS.gtk_tree_view_get_cell_area (handle, path, 0, cellRect);
	int[] tx = new int[1], ty = new int[1];
	OS.gtk_tree_view_convert_bin_window_to_tree_coords(handle, cellRect.x, cellRect.y, tx, ty);
	if (ty[0] < visibleRect.y ) {
		OS.gtk_tree_view_scroll_to_point (handle, -1, ty[0]);
	} else {
		int height = Math.min (visibleRect.height, cellRect.height);
		if (ty[0] + height > visibleRect.y + visibleRect.height) {
			ty[0] += cellRect.height - visibleRect.height;
			OS.gtk_tree_view_scroll_to_point (handle, -1, ty[0]);
		}
	}
	OS.gtk_tree_path_free (path);
	OS.g_free (iter);
}

}

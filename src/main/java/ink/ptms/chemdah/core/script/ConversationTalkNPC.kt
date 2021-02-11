package ink.ptms.chemdah.core.script

import io.izzel.taboolib.kotlin.kether.KetherFunction
import io.izzel.taboolib.kotlin.kether.KetherParser
import io.izzel.taboolib.kotlin.kether.ScriptParser
import io.izzel.taboolib.kotlin.kether.common.api.QuestAction
import io.izzel.taboolib.kotlin.kether.common.api.QuestContext
import io.izzel.taboolib.kotlin.kether.common.util.LocalizedException
import java.util.concurrent.CompletableFuture

class ConversationTalkNPC(val token: String) : QuestAction<Void>() {

    override fun process(frame: QuestContext.Frame): CompletableFuture<Void> {
        try {
            frame.getSession().npcSide.add(KetherFunction.parse(token, namespace = namespaceConversationNPC) { extend(frame.vars()) }.colored())
        } catch (e: LocalizedException) {
            e.print()
        } catch (e: Throwable) {
            e.printStackTrace()
        }
        return CompletableFuture.completedFuture(null)
    }

    companion object {

        @KetherParser(["talk"], namespace = "chemdah:conversation:npc")
        fun parser() = ScriptParser.parser {
            ConversationTalkNPC(it.nextToken())
        }
    }
}